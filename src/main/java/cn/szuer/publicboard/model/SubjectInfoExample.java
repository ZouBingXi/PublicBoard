package cn.szuer.publicboard.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SubjectInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SubjectInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andSubjectidIsNull() {
            addCriterion("subjectID is null");
            return (Criteria) this;
        }

        public Criteria andSubjectidIsNotNull() {
            addCriterion("subjectID is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectidEqualTo(Integer value) {
            addCriterion("subjectID =", value, "subjectid");
            return (Criteria) this;
        }

        public Criteria andSubjectidNotEqualTo(Integer value) {
            addCriterion("subjectID <>", value, "subjectid");
            return (Criteria) this;
        }

        public Criteria andSubjectidGreaterThan(Integer value) {
            addCriterion("subjectID >", value, "subjectid");
            return (Criteria) this;
        }

        public Criteria andSubjectidGreaterThanOrEqualTo(Integer value) {
            addCriterion("subjectID >=", value, "subjectid");
            return (Criteria) this;
        }

        public Criteria andSubjectidLessThan(Integer value) {
            addCriterion("subjectID <", value, "subjectid");
            return (Criteria) this;
        }

        public Criteria andSubjectidLessThanOrEqualTo(Integer value) {
            addCriterion("subjectID <=", value, "subjectid");
            return (Criteria) this;
        }

        public Criteria andSubjectidIn(List<Integer> values) {
            addCriterion("subjectID in", values, "subjectid");
            return (Criteria) this;
        }

        public Criteria andSubjectidNotIn(List<Integer> values) {
            addCriterion("subjectID not in", values, "subjectid");
            return (Criteria) this;
        }

        public Criteria andSubjectidBetween(Integer value1, Integer value2) {
            addCriterion("subjectID between", value1, value2, "subjectid");
            return (Criteria) this;
        }

        public Criteria andSubjectidNotBetween(Integer value1, Integer value2) {
            addCriterion("subjectID not between", value1, value2, "subjectid");
            return (Criteria) this;
        }

        public Criteria andUseridIsNull() {
            addCriterion("userID is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("userID is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(Integer value) {
            addCriterion("userID =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(Integer value) {
            addCriterion("userID <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(Integer value) {
            addCriterion("userID >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(Integer value) {
            addCriterion("userID >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(Integer value) {
            addCriterion("userID <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(Integer value) {
            addCriterion("userID <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<Integer> values) {
            addCriterion("userID in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<Integer> values) {
            addCriterion("userID not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(Integer value1, Integer value2) {
            addCriterion("userID between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(Integer value1, Integer value2) {
            addCriterion("userID not between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andSubjecttitleIsNull() {
            addCriterion("subjectTitle is null");
            return (Criteria) this;
        }

        public Criteria andSubjecttitleIsNotNull() {
            addCriterion("subjectTitle is not null");
            return (Criteria) this;
        }

        public Criteria andSubjecttitleEqualTo(String value) {
            addCriterion("subjectTitle =", value, "subjecttitle");
            return (Criteria) this;
        }

        public Criteria andSubjecttitleNotEqualTo(String value) {
            addCriterion("subjectTitle <>", value, "subjecttitle");
            return (Criteria) this;
        }

        public Criteria andSubjecttitleGreaterThan(String value) {
            addCriterion("subjectTitle >", value, "subjecttitle");
            return (Criteria) this;
        }

        public Criteria andSubjecttitleGreaterThanOrEqualTo(String value) {
            addCriterion("subjectTitle >=", value, "subjecttitle");
            return (Criteria) this;
        }

        public Criteria andSubjecttitleLessThan(String value) {
            addCriterion("subjectTitle <", value, "subjecttitle");
            return (Criteria) this;
        }

        public Criteria andSubjecttitleLessThanOrEqualTo(String value) {
            addCriterion("subjectTitle <=", value, "subjecttitle");
            return (Criteria) this;
        }

        public Criteria andSubjecttitleLike(String value) {
            addCriterion("subjectTitle like", value, "subjecttitle");
            return (Criteria) this;
        }

        public Criteria andSubjecttitleNotLike(String value) {
            addCriterion("subjectTitle not like", value, "subjecttitle");
            return (Criteria) this;
        }

        public Criteria andSubjecttitleIn(List<String> values) {
            addCriterion("subjectTitle in", values, "subjecttitle");
            return (Criteria) this;
        }

        public Criteria andSubjecttitleNotIn(List<String> values) {
            addCriterion("subjectTitle not in", values, "subjecttitle");
            return (Criteria) this;
        }

        public Criteria andSubjecttitleBetween(String value1, String value2) {
            addCriterion("subjectTitle between", value1, value2, "subjecttitle");
            return (Criteria) this;
        }

        public Criteria andSubjecttitleNotBetween(String value1, String value2) {
            addCriterion("subjectTitle not between", value1, value2, "subjecttitle");
            return (Criteria) this;
        }

        public Criteria andSendtimeIsNull() {
            addCriterion("sendTime is null");
            return (Criteria) this;
        }

        public Criteria andSendtimeIsNotNull() {
            addCriterion("sendTime is not null");
            return (Criteria) this;
        }

        public Criteria andSendtimeEqualTo(Date value) {
            addCriterion("sendTime =", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeNotEqualTo(Date value) {
            addCriterion("sendTime <>", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeGreaterThan(Date value) {
            addCriterion("sendTime >", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("sendTime >=", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeLessThan(Date value) {
            addCriterion("sendTime <", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeLessThanOrEqualTo(Date value) {
            addCriterion("sendTime <=", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeIn(List<Date> values) {
            addCriterion("sendTime in", values, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeNotIn(List<Date> values) {
            addCriterion("sendTime not in", values, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeBetween(Date value1, Date value2) {
            addCriterion("sendTime between", value1, value2, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeNotBetween(Date value1, Date value2) {
            addCriterion("sendTime not between", value1, value2, "sendtime");
            return (Criteria) this;
        }

        public Criteria andViewnumIsNull() {
            addCriterion("viewNum is null");
            return (Criteria) this;
        }

        public Criteria andViewnumIsNotNull() {
            addCriterion("viewNum is not null");
            return (Criteria) this;
        }

        public Criteria andViewnumEqualTo(Integer value) {
            addCriterion("viewNum =", value, "viewnum");
            return (Criteria) this;
        }

        public Criteria andViewnumNotEqualTo(Integer value) {
            addCriterion("viewNum <>", value, "viewnum");
            return (Criteria) this;
        }

        public Criteria andViewnumGreaterThan(Integer value) {
            addCriterion("viewNum >", value, "viewnum");
            return (Criteria) this;
        }

        public Criteria andViewnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("viewNum >=", value, "viewnum");
            return (Criteria) this;
        }

        public Criteria andViewnumLessThan(Integer value) {
            addCriterion("viewNum <", value, "viewnum");
            return (Criteria) this;
        }

        public Criteria andViewnumLessThanOrEqualTo(Integer value) {
            addCriterion("viewNum <=", value, "viewnum");
            return (Criteria) this;
        }

        public Criteria andViewnumIn(List<Integer> values) {
            addCriterion("viewNum in", values, "viewnum");
            return (Criteria) this;
        }

        public Criteria andViewnumNotIn(List<Integer> values) {
            addCriterion("viewNum not in", values, "viewnum");
            return (Criteria) this;
        }

        public Criteria andViewnumBetween(Integer value1, Integer value2) {
            addCriterion("viewNum between", value1, value2, "viewnum");
            return (Criteria) this;
        }

        public Criteria andViewnumNotBetween(Integer value1, Integer value2) {
            addCriterion("viewNum not between", value1, value2, "viewnum");
            return (Criteria) this;
        }

        public Criteria andLikenumIsNull() {
            addCriterion("likeNum is null");
            return (Criteria) this;
        }

        public Criteria andLikenumIsNotNull() {
            addCriterion("likeNum is not null");
            return (Criteria) this;
        }

        public Criteria andLikenumEqualTo(Integer value) {
            addCriterion("likeNum =", value, "likenum");
            return (Criteria) this;
        }

        public Criteria andLikenumNotEqualTo(Integer value) {
            addCriterion("likeNum <>", value, "likenum");
            return (Criteria) this;
        }

        public Criteria andLikenumGreaterThan(Integer value) {
            addCriterion("likeNum >", value, "likenum");
            return (Criteria) this;
        }

        public Criteria andLikenumGreaterThanOrEqualTo(Integer value) {
            addCriterion("likeNum >=", value, "likenum");
            return (Criteria) this;
        }

        public Criteria andLikenumLessThan(Integer value) {
            addCriterion("likeNum <", value, "likenum");
            return (Criteria) this;
        }

        public Criteria andLikenumLessThanOrEqualTo(Integer value) {
            addCriterion("likeNum <=", value, "likenum");
            return (Criteria) this;
        }

        public Criteria andLikenumIn(List<Integer> values) {
            addCriterion("likeNum in", values, "likenum");
            return (Criteria) this;
        }

        public Criteria andLikenumNotIn(List<Integer> values) {
            addCriterion("likeNum not in", values, "likenum");
            return (Criteria) this;
        }

        public Criteria andLikenumBetween(Integer value1, Integer value2) {
            addCriterion("likeNum between", value1, value2, "likenum");
            return (Criteria) this;
        }

        public Criteria andLikenumNotBetween(Integer value1, Integer value2) {
            addCriterion("likeNum not between", value1, value2, "likenum");
            return (Criteria) this;
        }

        public Criteria andSubjecttypeidIsNull() {
            addCriterion("subjectTypeID is null");
            return (Criteria) this;
        }

        public Criteria andSubjecttypeidIsNotNull() {
            addCriterion("subjectTypeID is not null");
            return (Criteria) this;
        }

        public Criteria andSubjecttypeidEqualTo(Integer value) {
            addCriterion("subjectTypeID =", value, "subjecttypeid");
            return (Criteria) this;
        }

        public Criteria andSubjecttypeidNotEqualTo(Integer value) {
            addCriterion("subjectTypeID <>", value, "subjecttypeid");
            return (Criteria) this;
        }

        public Criteria andSubjecttypeidGreaterThan(Integer value) {
            addCriterion("subjectTypeID >", value, "subjecttypeid");
            return (Criteria) this;
        }

        public Criteria andSubjecttypeidGreaterThanOrEqualTo(Integer value) {
            addCriterion("subjectTypeID >=", value, "subjecttypeid");
            return (Criteria) this;
        }

        public Criteria andSubjecttypeidLessThan(Integer value) {
            addCriterion("subjectTypeID <", value, "subjecttypeid");
            return (Criteria) this;
        }

        public Criteria andSubjecttypeidLessThanOrEqualTo(Integer value) {
            addCriterion("subjectTypeID <=", value, "subjecttypeid");
            return (Criteria) this;
        }

        public Criteria andSubjecttypeidIn(List<Integer> values) {
            addCriterion("subjectTypeID in", values, "subjecttypeid");
            return (Criteria) this;
        }

        public Criteria andSubjecttypeidNotIn(List<Integer> values) {
            addCriterion("subjectTypeID not in", values, "subjecttypeid");
            return (Criteria) this;
        }

        public Criteria andSubjecttypeidBetween(Integer value1, Integer value2) {
            addCriterion("subjectTypeID between", value1, value2, "subjecttypeid");
            return (Criteria) this;
        }

        public Criteria andSubjecttypeidNotBetween(Integer value1, Integer value2) {
            addCriterion("subjectTypeID not between", value1, value2, "subjecttypeid");
            return (Criteria) this;
        }

        public Criteria andAnonymousstateIsNull() {
            addCriterion("anonymousState is null");
            return (Criteria) this;
        }

        public Criteria andAnonymousstateIsNotNull() {
            addCriterion("anonymousState is not null");
            return (Criteria) this;
        }

        public Criteria andAnonymousstateEqualTo(Boolean value) {
            addCriterion("anonymousState =", value, "anonymousstate");
            return (Criteria) this;
        }

        public Criteria andAnonymousstateNotEqualTo(Boolean value) {
            addCriterion("anonymousState <>", value, "anonymousstate");
            return (Criteria) this;
        }

        public Criteria andAnonymousstateGreaterThan(Boolean value) {
            addCriterion("anonymousState >", value, "anonymousstate");
            return (Criteria) this;
        }

        public Criteria andAnonymousstateGreaterThanOrEqualTo(Boolean value) {
            addCriterion("anonymousState >=", value, "anonymousstate");
            return (Criteria) this;
        }

        public Criteria andAnonymousstateLessThan(Boolean value) {
            addCriterion("anonymousState <", value, "anonymousstate");
            return (Criteria) this;
        }

        public Criteria andAnonymousstateLessThanOrEqualTo(Boolean value) {
            addCriterion("anonymousState <=", value, "anonymousstate");
            return (Criteria) this;
        }

        public Criteria andAnonymousstateIn(List<Boolean> values) {
            addCriterion("anonymousState in", values, "anonymousstate");
            return (Criteria) this;
        }

        public Criteria andAnonymousstateNotIn(List<Boolean> values) {
            addCriterion("anonymousState not in", values, "anonymousstate");
            return (Criteria) this;
        }

        public Criteria andAnonymousstateBetween(Boolean value1, Boolean value2) {
            addCriterion("anonymousState between", value1, value2, "anonymousstate");
            return (Criteria) this;
        }

        public Criteria andAnonymousstateNotBetween(Boolean value1, Boolean value2) {
            addCriterion("anonymousState not between", value1, value2, "anonymousstate");
            return (Criteria) this;
        }

        public Criteria andTopstateIsNull() {
            addCriterion("topState is null");
            return (Criteria) this;
        }

        public Criteria andTopstateIsNotNull() {
            addCriterion("topState is not null");
            return (Criteria) this;
        }

        public Criteria andTopstateEqualTo(Boolean value) {
            addCriterion("topState =", value, "topstate");
            return (Criteria) this;
        }

        public Criteria andTopstateNotEqualTo(Boolean value) {
            addCriterion("topState <>", value, "topstate");
            return (Criteria) this;
        }

        public Criteria andTopstateGreaterThan(Boolean value) {
            addCriterion("topState >", value, "topstate");
            return (Criteria) this;
        }

        public Criteria andTopstateGreaterThanOrEqualTo(Boolean value) {
            addCriterion("topState >=", value, "topstate");
            return (Criteria) this;
        }

        public Criteria andTopstateLessThan(Boolean value) {
            addCriterion("topState <", value, "topstate");
            return (Criteria) this;
        }

        public Criteria andTopstateLessThanOrEqualTo(Boolean value) {
            addCriterion("topState <=", value, "topstate");
            return (Criteria) this;
        }

        public Criteria andTopstateIn(List<Boolean> values) {
            addCriterion("topState in", values, "topstate");
            return (Criteria) this;
        }

        public Criteria andTopstateNotIn(List<Boolean> values) {
            addCriterion("topState not in", values, "topstate");
            return (Criteria) this;
        }

        public Criteria andTopstateBetween(Boolean value1, Boolean value2) {
            addCriterion("topState between", value1, value2, "topstate");
            return (Criteria) this;
        }

        public Criteria andTopstateNotBetween(Boolean value1, Boolean value2) {
            addCriterion("topState not between", value1, value2, "topstate");
            return (Criteria) this;
        }

        public Criteria andHotstateIsNull() {
            addCriterion("hotState is null");
            return (Criteria) this;
        }

        public Criteria andHotstateIsNotNull() {
            addCriterion("hotState is not null");
            return (Criteria) this;
        }

        public Criteria andHotstateEqualTo(Boolean value) {
            addCriterion("hotState =", value, "hotstate");
            return (Criteria) this;
        }

        public Criteria andHotstateNotEqualTo(Boolean value) {
            addCriterion("hotState <>", value, "hotstate");
            return (Criteria) this;
        }

        public Criteria andHotstateGreaterThan(Boolean value) {
            addCriterion("hotState >", value, "hotstate");
            return (Criteria) this;
        }

        public Criteria andHotstateGreaterThanOrEqualTo(Boolean value) {
            addCriterion("hotState >=", value, "hotstate");
            return (Criteria) this;
        }

        public Criteria andHotstateLessThan(Boolean value) {
            addCriterion("hotState <", value, "hotstate");
            return (Criteria) this;
        }

        public Criteria andHotstateLessThanOrEqualTo(Boolean value) {
            addCriterion("hotState <=", value, "hotstate");
            return (Criteria) this;
        }

        public Criteria andHotstateIn(List<Boolean> values) {
            addCriterion("hotState in", values, "hotstate");
            return (Criteria) this;
        }

        public Criteria andHotstateNotIn(List<Boolean> values) {
            addCriterion("hotState not in", values, "hotstate");
            return (Criteria) this;
        }

        public Criteria andHotstateBetween(Boolean value1, Boolean value2) {
            addCriterion("hotState between", value1, value2, "hotstate");
            return (Criteria) this;
        }

        public Criteria andHotstateNotBetween(Boolean value1, Boolean value2) {
            addCriterion("hotState not between", value1, value2, "hotstate");
            return (Criteria) this;
        }

        public Criteria andExaminestateIsNull() {
            addCriterion("examineState is null");
            return (Criteria) this;
        }

        public Criteria andExaminestateIsNotNull() {
            addCriterion("examineState is not null");
            return (Criteria) this;
        }

        public Criteria andExaminestateEqualTo(Boolean value) {
            addCriterion("examineState =", value, "examinestate");
            return (Criteria) this;
        }

        public Criteria andExaminestateNotEqualTo(Boolean value) {
            addCriterion("examineState <>", value, "examinestate");
            return (Criteria) this;
        }

        public Criteria andExaminestateGreaterThan(Boolean value) {
            addCriterion("examineState >", value, "examinestate");
            return (Criteria) this;
        }

        public Criteria andExaminestateGreaterThanOrEqualTo(Boolean value) {
            addCriterion("examineState >=", value, "examinestate");
            return (Criteria) this;
        }

        public Criteria andExaminestateLessThan(Boolean value) {
            addCriterion("examineState <", value, "examinestate");
            return (Criteria) this;
        }

        public Criteria andExaminestateLessThanOrEqualTo(Boolean value) {
            addCriterion("examineState <=", value, "examinestate");
            return (Criteria) this;
        }

        public Criteria andExaminestateIn(List<Boolean> values) {
            addCriterion("examineState in", values, "examinestate");
            return (Criteria) this;
        }

        public Criteria andExaminestateNotIn(List<Boolean> values) {
            addCriterion("examineState not in", values, "examinestate");
            return (Criteria) this;
        }

        public Criteria andExaminestateBetween(Boolean value1, Boolean value2) {
            addCriterion("examineState between", value1, value2, "examinestate");
            return (Criteria) this;
        }

        public Criteria andExaminestateNotBetween(Boolean value1, Boolean value2) {
            addCriterion("examineState not between", value1, value2, "examinestate");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}