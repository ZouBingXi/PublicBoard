package cn.szuer.publicboard.model;

import java.util.ArrayList;
import java.util.List;

public class NewsImageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NewsImageExample() {
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

        public Criteria andImageuuidIsNull() {
            addCriterion("imageUUID is null");
            return (Criteria) this;
        }

        public Criteria andImageuuidIsNotNull() {
            addCriterion("imageUUID is not null");
            return (Criteria) this;
        }

        public Criteria andImageuuidEqualTo(String value) {
            addCriterion("imageUUID =", value, "imageuuid");
            return (Criteria) this;
        }

        public Criteria andImageuuidNotEqualTo(String value) {
            addCriterion("imageUUID <>", value, "imageuuid");
            return (Criteria) this;
        }

        public Criteria andImageuuidGreaterThan(String value) {
            addCriterion("imageUUID >", value, "imageuuid");
            return (Criteria) this;
        }

        public Criteria andImageuuidGreaterThanOrEqualTo(String value) {
            addCriterion("imageUUID >=", value, "imageuuid");
            return (Criteria) this;
        }

        public Criteria andImageuuidLessThan(String value) {
            addCriterion("imageUUID <", value, "imageuuid");
            return (Criteria) this;
        }

        public Criteria andImageuuidLessThanOrEqualTo(String value) {
            addCriterion("imageUUID <=", value, "imageuuid");
            return (Criteria) this;
        }

        public Criteria andImageuuidLike(String value) {
            addCriterion("imageUUID like", value, "imageuuid");
            return (Criteria) this;
        }

        public Criteria andImageuuidNotLike(String value) {
            addCriterion("imageUUID not like", value, "imageuuid");
            return (Criteria) this;
        }

        public Criteria andImageuuidIn(List<String> values) {
            addCriterion("imageUUID in", values, "imageuuid");
            return (Criteria) this;
        }

        public Criteria andImageuuidNotIn(List<String> values) {
            addCriterion("imageUUID not in", values, "imageuuid");
            return (Criteria) this;
        }

        public Criteria andImageuuidBetween(String value1, String value2) {
            addCriterion("imageUUID between", value1, value2, "imageuuid");
            return (Criteria) this;
        }

        public Criteria andImageuuidNotBetween(String value1, String value2) {
            addCriterion("imageUUID not between", value1, value2, "imageuuid");
            return (Criteria) this;
        }

        public Criteria andNewsidIsNull() {
            addCriterion("newsID is null");
            return (Criteria) this;
        }

        public Criteria andNewsidIsNotNull() {
            addCriterion("newsID is not null");
            return (Criteria) this;
        }

        public Criteria andNewsidEqualTo(Integer value) {
            addCriterion("newsID =", value, "newsid");
            return (Criteria) this;
        }

        public Criteria andNewsidNotEqualTo(Integer value) {
            addCriterion("newsID <>", value, "newsid");
            return (Criteria) this;
        }

        public Criteria andNewsidGreaterThan(Integer value) {
            addCriterion("newsID >", value, "newsid");
            return (Criteria) this;
        }

        public Criteria andNewsidGreaterThanOrEqualTo(Integer value) {
            addCriterion("newsID >=", value, "newsid");
            return (Criteria) this;
        }

        public Criteria andNewsidLessThan(Integer value) {
            addCriterion("newsID <", value, "newsid");
            return (Criteria) this;
        }

        public Criteria andNewsidLessThanOrEqualTo(Integer value) {
            addCriterion("newsID <=", value, "newsid");
            return (Criteria) this;
        }

        public Criteria andNewsidIn(List<Integer> values) {
            addCriterion("newsID in", values, "newsid");
            return (Criteria) this;
        }

        public Criteria andNewsidNotIn(List<Integer> values) {
            addCriterion("newsID not in", values, "newsid");
            return (Criteria) this;
        }

        public Criteria andNewsidBetween(Integer value1, Integer value2) {
            addCriterion("newsID between", value1, value2, "newsid");
            return (Criteria) this;
        }

        public Criteria andNewsidNotBetween(Integer value1, Integer value2) {
            addCriterion("newsID not between", value1, value2, "newsid");
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