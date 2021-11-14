package cn.szuer.publicboard.model;

import java.util.ArrayList;
import java.util.List;

public class SubjectStateExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SubjectStateExample() {
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

        public Criteria andTopstateIsNull() {
            addCriterion("topState is null");
            return (Criteria) this;
        }

        public Criteria andTopstateIsNotNull() {
            addCriterion("topState is not null");
            return (Criteria) this;
        }

        public Criteria andTopstateEqualTo(Byte value) {
            addCriterion("topState =", value, "topstate");
            return (Criteria) this;
        }

        public Criteria andTopstateNotEqualTo(Byte value) {
            addCriterion("topState <>", value, "topstate");
            return (Criteria) this;
        }

        public Criteria andTopstateGreaterThan(Byte value) {
            addCriterion("topState >", value, "topstate");
            return (Criteria) this;
        }

        public Criteria andTopstateGreaterThanOrEqualTo(Byte value) {
            addCriterion("topState >=", value, "topstate");
            return (Criteria) this;
        }

        public Criteria andTopstateLessThan(Byte value) {
            addCriterion("topState <", value, "topstate");
            return (Criteria) this;
        }

        public Criteria andTopstateLessThanOrEqualTo(Byte value) {
            addCriterion("topState <=", value, "topstate");
            return (Criteria) this;
        }

        public Criteria andTopstateIn(List<Byte> values) {
            addCriterion("topState in", values, "topstate");
            return (Criteria) this;
        }

        public Criteria andTopstateNotIn(List<Byte> values) {
            addCriterion("topState not in", values, "topstate");
            return (Criteria) this;
        }

        public Criteria andTopstateBetween(Byte value1, Byte value2) {
            addCriterion("topState between", value1, value2, "topstate");
            return (Criteria) this;
        }

        public Criteria andTopstateNotBetween(Byte value1, Byte value2) {
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

        public Criteria andHotstateEqualTo(Byte value) {
            addCriterion("hotState =", value, "hotstate");
            return (Criteria) this;
        }

        public Criteria andHotstateNotEqualTo(Byte value) {
            addCriterion("hotState <>", value, "hotstate");
            return (Criteria) this;
        }

        public Criteria andHotstateGreaterThan(Byte value) {
            addCriterion("hotState >", value, "hotstate");
            return (Criteria) this;
        }

        public Criteria andHotstateGreaterThanOrEqualTo(Byte value) {
            addCriterion("hotState >=", value, "hotstate");
            return (Criteria) this;
        }

        public Criteria andHotstateLessThan(Byte value) {
            addCriterion("hotState <", value, "hotstate");
            return (Criteria) this;
        }

        public Criteria andHotstateLessThanOrEqualTo(Byte value) {
            addCriterion("hotState <=", value, "hotstate");
            return (Criteria) this;
        }

        public Criteria andHotstateIn(List<Byte> values) {
            addCriterion("hotState in", values, "hotstate");
            return (Criteria) this;
        }

        public Criteria andHotstateNotIn(List<Byte> values) {
            addCriterion("hotState not in", values, "hotstate");
            return (Criteria) this;
        }

        public Criteria andHotstateBetween(Byte value1, Byte value2) {
            addCriterion("hotState between", value1, value2, "hotstate");
            return (Criteria) this;
        }

        public Criteria andHotstateNotBetween(Byte value1, Byte value2) {
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

        public Criteria andExaminestateEqualTo(Byte value) {
            addCriterion("examineState =", value, "examinestate");
            return (Criteria) this;
        }

        public Criteria andExaminestateNotEqualTo(Byte value) {
            addCriterion("examineState <>", value, "examinestate");
            return (Criteria) this;
        }

        public Criteria andExaminestateGreaterThan(Byte value) {
            addCriterion("examineState >", value, "examinestate");
            return (Criteria) this;
        }

        public Criteria andExaminestateGreaterThanOrEqualTo(Byte value) {
            addCriterion("examineState >=", value, "examinestate");
            return (Criteria) this;
        }

        public Criteria andExaminestateLessThan(Byte value) {
            addCriterion("examineState <", value, "examinestate");
            return (Criteria) this;
        }

        public Criteria andExaminestateLessThanOrEqualTo(Byte value) {
            addCriterion("examineState <=", value, "examinestate");
            return (Criteria) this;
        }

        public Criteria andExaminestateIn(List<Byte> values) {
            addCriterion("examineState in", values, "examinestate");
            return (Criteria) this;
        }

        public Criteria andExaminestateNotIn(List<Byte> values) {
            addCriterion("examineState not in", values, "examinestate");
            return (Criteria) this;
        }

        public Criteria andExaminestateBetween(Byte value1, Byte value2) {
            addCriterion("examineState between", value1, value2, "examinestate");
            return (Criteria) this;
        }

        public Criteria andExaminestateNotBetween(Byte value1, Byte value2) {
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