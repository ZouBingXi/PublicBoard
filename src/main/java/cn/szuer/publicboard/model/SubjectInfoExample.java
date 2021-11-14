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

        public Criteria andSubjecttypeIsNull() {
            addCriterion("subjectType is null");
            return (Criteria) this;
        }

        public Criteria andSubjecttypeIsNotNull() {
            addCriterion("subjectType is not null");
            return (Criteria) this;
        }

        public Criteria andSubjecttypeEqualTo(Integer value) {
            addCriterion("subjectType =", value, "subjecttype");
            return (Criteria) this;
        }

        public Criteria andSubjecttypeNotEqualTo(Integer value) {
            addCriterion("subjectType <>", value, "subjecttype");
            return (Criteria) this;
        }

        public Criteria andSubjecttypeGreaterThan(Integer value) {
            addCriterion("subjectType >", value, "subjecttype");
            return (Criteria) this;
        }

        public Criteria andSubjecttypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("subjectType >=", value, "subjecttype");
            return (Criteria) this;
        }

        public Criteria andSubjecttypeLessThan(Integer value) {
            addCriterion("subjectType <", value, "subjecttype");
            return (Criteria) this;
        }

        public Criteria andSubjecttypeLessThanOrEqualTo(Integer value) {
            addCriterion("subjectType <=", value, "subjecttype");
            return (Criteria) this;
        }

        public Criteria andSubjecttypeIn(List<Integer> values) {
            addCriterion("subjectType in", values, "subjecttype");
            return (Criteria) this;
        }

        public Criteria andSubjecttypeNotIn(List<Integer> values) {
            addCriterion("subjectType not in", values, "subjecttype");
            return (Criteria) this;
        }

        public Criteria andSubjecttypeBetween(Integer value1, Integer value2) {
            addCriterion("subjectType between", value1, value2, "subjecttype");
            return (Criteria) this;
        }

        public Criteria andSubjecttypeNotBetween(Integer value1, Integer value2) {
            addCriterion("subjectType not between", value1, value2, "subjecttype");
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