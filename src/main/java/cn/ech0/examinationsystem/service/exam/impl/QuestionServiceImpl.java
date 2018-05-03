package cn.ech0.examinationsystem.service.exam.impl;

import cn.ech0.examinationsystem.convertor.QuestionEntityConvertor;
import cn.ech0.examinationsystem.dao.exam.QuestionDao;
import cn.ech0.examinationsystem.dto.ResultDTO;
import cn.ech0.examinationsystem.dto.exam.QuestionDTO;
import cn.ech0.examinationsystem.entity.exam.QuestionEntity;
import cn.ech0.examinationsystem.enums.ResponseCodeEnum;
import cn.ech0.examinationsystem.enums.question.QuestionStatusEnum;
import cn.ech0.examinationsystem.exception.BaseServerException;
import cn.ech0.examinationsystem.service.exam.IQuestionService;
import cn.ech0.examinationsystem.validate.QuestionValidator;
import cn.ech0.examinationsystem.wrapper.QuestionWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static cn.ech0.examinationsystem.util.DateUtil.getCurrentDate;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 04/29/2018 09:24 PM
 */
@Service
@Slf4j
public class QuestionServiceImpl implements IQuestionService {
    @Autowired
    private QuestionDao questionDao;

    /**
     * @param questionId
     * @return
     */
    @Override
    public QuestionDTO findOne(Long questionId) {
        QuestionEntity questionEntity = questionDao.findOne(questionId);
        if (questionEntity == null) {
            return null;
        }
        return QuestionEntityConvertor.convertoQuestionDTO(questionEntity);
    }

    /**
     * 克隆题目
     *
     * @param userId
     * @param userName
     * @param questionId
     * @return
     */
    @Override
    public QuestionDTO clone(String userId, String userName, Long questionId) {
        QuestionEntity origin = questionDao.findOne(questionId);
        if (origin == null) {
            throw new BaseServerException(ResponseCodeEnum.VIOLATION_OPERATION.getCode(),
                    "question id 不存在 ！");
        }
        if (origin.getStatus().equals(QuestionStatusEnum.DELETED.getCode())) {
            throw new BaseServerException(ResponseCodeEnum.VIOLATION_OPERATION.getCode(),
                    "无法克隆，该题目已被删除 ！");
        }
        if (origin.getUserId().equals(userId)) {
            throw new BaseServerException(ResponseCodeEnum.VIOLATION_OPERATION.getCode(),
                    "请不要克隆自己的题目 ！");
        }
        QuestionEntity questionEntity = new QuestionEntity();
        BeanUtils.copyProperties(origin, questionEntity);
        questionEntity.setUserId(userId);
        questionEntity.setUserName(userName);
        questionEntity.setCloneFrom(questionId);

        questionEntity.setUseTimes(0L);
        questionEntity.setErrorTimes(0L);
        questionEntity.setFavoriteTimes(0);
        questionEntity.setCloneTimes(0);
        try {
            questionEntity = questionDao.saveAndFlush(questionEntity);
        } catch (Exception e) {
            throw new BaseServerException(ResponseCodeEnum.VIOLATION_OPERATION.getCode(),
                    "克隆失败，该题目已存在  ！");
        }
        return QuestionEntityConvertor.convertoQuestionDTO(questionEntity);

    }

    /**
     * 删除题目,逻辑删除
     *
     * @param userId
     * @param questionId
     * @return
     */
    @Override
    public void delete(String userId, Long questionId) {
        QuestionEntity questionEntity = questionDao.findOne(questionId);
        if (questionEntity == null) {
            throw new BaseServerException(ResponseCodeEnum.VIOLATION_OPERATION.getCode(),
                    "question id 不存在 ！");
        }
        if (!questionEntity.getUserId().equals(userId)) {
            throw new BaseServerException(ResponseCodeEnum.NO_PRIVILEGE);
        }
        questionEntity.setStatus(QuestionStatusEnum.DELETED.getCode());
        questionDao.saveAndFlush(questionEntity);
    }

    /**
     * 修改题目
     *
     * @param userId
     * @param questionEntity 非空字段必须是完整的
     * @return
     */
    @Override
    public QuestionDTO update(String userId, QuestionEntity questionEntity) {

        QuestionEntity origin = questionDao.findOne(questionEntity.getId());
        if (origin == null || origin.getUserId().equals(questionEntity.getUserId())) {
            throw new BaseServerException(ResponseCodeEnum.VIOLATION_OPERATION);
        }
        ResultDTO<String> resultDTO = QuestionValidator.check(questionEntity);
        if (!resultDTO.isSuccess()) {
            throw new BaseServerException(ResponseCodeEnum.ILLEGAL_ARGUMENT.getCode(), resultDTO.getData());
        }
        // 保留原有的基础属性
        questionEntity.setUserId(userId);
        questionEntity.setUserName(origin.getUserName());
        questionEntity.setCreateTime(origin.getCreateTime());
        questionEntity.setUseTimes(origin.getUseTimes());
        questionEntity.setErrorTimes(origin.getErrorTimes());
        // 更新修改时间
        questionEntity.setUpdateTime(getCurrentDate());
        questionEntity = questionDao.saveAndFlush(questionEntity);
        return QuestionEntityConvertor.convertoQuestionDTO(questionEntity);
    }

    /**
     * 添加题目
     *
     * @param userId
     * @param userName
     * @param questionEntity
     * @return
     */
    @Override
    public QuestionDTO add(String userId, String userName, QuestionEntity questionEntity) {
        ResultDTO<String> resultDTO = QuestionValidator.check(questionEntity);
        if (!resultDTO.isSuccess()) {
            throw new BaseServerException(ResponseCodeEnum.ILLEGAL_ARGUMENT.getCode(), resultDTO.getData());
        }
        questionEntity.setUserId(userId);
        questionEntity.setCreateTime(getCurrentDate());
        questionEntity.setUpdateTime(getCurrentDate());
        questionEntity.setUserName(userName);
        try {
            questionEntity = questionDao.saveAndFlush(questionEntity);
        } catch (Exception e) {
            throw new BaseServerException(ResponseCodeEnum.VIOLATION_OPERATION.getCode(), "该题目已存在，请不要重复添加！");
        }
        return QuestionEntityConvertor.convertoQuestionDTO(questionEntity);
    }

    /**
     * 分页查找属于该用户的所有题目
     *
     * @param userId   ! necessary
     * @param category
     * @param type
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public QuestionWrapper findQuestionsByUserId(String userId, Integer category, Integer type, Integer pageNum, Integer pageSize) {
        Specification<QuestionEntity> specification =
                (root, query, cb) -> {
                    List<Predicate> predicates = new LinkedList<>();
                    // userId
                    Path<String> $userId = root.get("userId");
                    Predicate _userId = cb.equal($userId, userId);
                    predicates.add(_userId);
                    // status
                    Path<Integer> $status = root.get("status");
                    Predicate _status = cb.equal($status, QuestionStatusEnum.VALID.getCode());
                    predicates.add(_status);

                    if (category != null) {
                        // title
                        Path<Integer> $category = root.get("category");
                        Predicate _category = cb.equal($category, category);
                        predicates.add(_category);
                    }
                    if (type != null) {
                        // title
                        Path<Integer> $type = root.get("type");
                        Predicate _type = cb.equal($type, type);
                        predicates.add(_type);
                    }

                    return cb.and(predicates.toArray(new Predicate[]{}));
                };
        // 按照修改时间降序
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        Pageable pageable = new PageRequest(pageNum - 1, pageSize, sort);

        Page<QuestionEntity> page = questionDao.findAll(specification, pageable);
        List<QuestionEntity> questionEntities = page.getContent();
        List<QuestionDTO> questionDTOList = new ArrayList<>(pageSize);
        questionEntities.forEach(questionEntity -> {
            questionDTOList.add(QuestionEntityConvertor.convertoQuestionDTO(questionEntity));
        });
        QuestionWrapper questionWrapper = new QuestionWrapper();
        questionWrapper.setCurrentPage(pageNum);
        questionWrapper.setTotalPages(page.getTotalPages());
        questionWrapper.setTotalElements(page.getTotalElements());
        questionWrapper.setContent(questionDTOList);
        return questionWrapper;
    }

    /**
     * 给定条件分页模糊搜索题目
     * 按照错误次数以及使用次数降序
     *
     * @param keyword
     * @param category
     * @param type
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public QuestionWrapper searchQuestions(String keyword, Integer category, Integer type, Integer pageNum, Integer pageSize) {
        Specification<QuestionEntity> specification =
                (root, query, cb) -> {
                    List<Predicate> predicates = new LinkedList<>();
                    // status
                    Path<Integer> $status = root.get("status");
                    Predicate _status = cb.equal($status, QuestionStatusEnum.VALID.getCode());
                    predicates.add(_status);

                    if (!StringUtils.isEmpty(keyword)) {
                        // title
                        Path<String> $title = root.get("title");
                        Predicate _title = cb.like($title, "%" + keyword + "%");
                        predicates.add(_title);
                    }
                    if (category != null) {
                        // title
                        Path<Integer> $category = root.get("category");
                        Predicate _category = cb.equal($category, category);
                        predicates.add(_category);
                    }
                    if (type != null) {
                        // title
                        Path<Integer> $type = root.get("type");
                        Predicate _type = cb.equal($type, type);
                        predicates.add(_type);
                    }
                    return cb.and(predicates.toArray(new Predicate[]{}));
                };
        // 按照错误次数以及使用次数降序
        Sort sort = new Sort(Sort.Direction.DESC, "errorTimes");
        sort = sort.and(new Sort(Sort.Direction.DESC, "useTimes"));

        Pageable pageable = new PageRequest(pageNum - 1, pageSize, sort);
        Page<QuestionEntity> page = questionDao.findAll(specification, pageable);
        List<QuestionEntity> questionEntities = page.getContent();
        List<QuestionDTO> questionDTOList = new ArrayList<>(pageSize);
        questionEntities.forEach(questionEntity -> {
            questionDTOList.add(QuestionEntityConvertor.convertoQuestionDTO(questionEntity));
        });
        QuestionWrapper questionWrapper = new QuestionWrapper();
        questionWrapper.setCurrentPage(pageNum);
        questionWrapper.setTotalPages(page.getTotalPages());
        questionWrapper.setTotalElements(page.getTotalElements());
        questionWrapper.setContent(questionDTOList);
        return questionWrapper;
    }
}
