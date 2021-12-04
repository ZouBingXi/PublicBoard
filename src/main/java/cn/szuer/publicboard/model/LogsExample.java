package cn.szuer.publicboard.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LogsExample() {
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

        public Criteria andLogsidIsNull() {
            addCriterion("logsID is null");
            return (Criteria) this;
        }

        public Criteria andLogsidIsNotNull() {
            addCriterion("logsID is not null");
            return (Criteria) this;
        }

        public Criteria andLogsidEqualTo(Integer value) {
            addCriterion("logsID =", value, "logsid");
            return (Criteria) this;
        }

        public Criteria andLogsidNotEqualTo(Integer value) {
            addCriterion("logsID <>", value, "logsid");
            return (Criteria) this;
        }

        public Criteria andLogsidGreaterThan(Integer value) {
            addCriterion("logsID >", value, "logsid");
            return (Criteria) this;
        }

        public Criteria andLogsidGreaterThanOrEqualTo(Integer value) {
            addCriterion("logsID >=", value, "logsid");
            return (Criteria) this;
        }

        public Criteria andLogsidLessThan(Integer value) {
            addCriterion("logsID <", value, "logsid");
            return (Criteria) this;
        }

        public Criteria andLogsidLessThanOrEqualTo(Integer value) {
            addCriterion("logsID <=", value, "logsid");
            return (Criteria) this;
        }

        public Criteria andLogsidIn(List<Integer> values) {
            addCriterion("logsID in", values, "logsid");
            return (Criteria) this;
        }

        public Criteria andLogsidNotIn(List<Integer> values) {
            addCriterion("logsID not in", values, "logsid");
            return (Criteria) this;
        }

        public Criteria andLogsidBetween(Integer value1, Integer value2) {
            addCriterion("logsID between", value1, value2, "logsid");
            return (Criteria) this;
        }

        public Criteria andLogsidNotBetween(Integer value1, Integer value2) {
            addCriterion("logsID not between", value1, value2, "logsid");
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

        public Criteria andOperatetypeIsNull() {
            addCriterion("operateType is null");
            return (Criteria) this;
        }

        public Criteria andOperatetypeIsNotNull() {
            addCriterion("operateType is not null");
            return (Criteria) this;
        }

        public Criteria andOperatetypeEqualTo(Integer value) {
            addCriterion("operateType =", value, "operatetype");
            return (Criteria) this;
        }

        public Criteria andOperatetypeNotEqualTo(Integer value) {
            addCriterion("operateType <>", value, "operatetype");
            return (Criteria) this;
        }

        public Criteria andOperatetypeGreaterThan(Integer value) {
            addCriterion("operateType >", value, "operatetype");
            return (Criteria) this;
        }

        public Criteria andOperatetypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("operateType >=", value, "operatetype");
            return (Criteria) this;
        }

        public Criteria andOperatetypeLessThan(Integer value) {
            addCriterion("operateType <", value, "operatetype");
            return (Criteria) this;
        }

        public Criteria andOperatetypeLessThanOrEqualTo(Integer value) {
            addCriterion("operateType <=", value, "operatetype");
            return (Criteria) this;
        }

        public Criteria andOperatetypeIn(List<Integer> values) {
            addCriterion("operateType in", values, "operatetype");
            return (Criteria) this;
        }

        public Criteria andOperatetypeNotIn(List<Integer> values) {
            addCriterion("operateType not in", values, "operatetype");
            return (Criteria) this;
        }

        public Criteria andOperatetypeBetween(Integer value1, Integer value2) {
            addCriterion("operateType between", value1, value2, "operatetype");
            return (Criteria) this;
        }

        public Criteria andOperatetypeNotBetween(Integer value1, Integer value2) {
            addCriterion("operateType not between", value1, value2, "operatetype");
            return (Criteria) this;
        }

        public Criteria andOperateinfoIsNull() {
            addCriterion("operateInfo is null");
            return (Criteria) this;
        }

        public Criteria andOperateinfoIsNotNull() {
            addCriterion("operateInfo is not null");
            return (Criteria) this;
        }

        public Criteria andOperateinfoEqualTo(String value) {
            addCriterion("operateInfo =", value, "operateinfo");
            return (Criteria) this;
        }

        public Criteria andOperateinfoNotEqualTo(String value) {
            addCriterion("operateInfo <>", value, "operateinfo");
            return (Criteria) this;
        }

        public Criteria andOperateinfoGreaterThan(String value) {
            addCriterion("operateInfo >", value, "operateinfo");
            return (Criteria) this;
        }

        public Criteria andOperateinfoGreaterThanOrEqualTo(String value) {
            addCriterion("operateInfo >=", value, "operateinfo");
            return (Criteria) this;
        }

        public Criteria andOperateinfoLessThan(String value) {
            addCriterion("operateInfo <", value, "operateinfo");
            return (Criteria) this;
        }

        public Criteria andOperateinfoLessThanOrEqualTo(String value) {
            addCriterion("operateInfo <=", value, "operateinfo");
            return (Criteria) this;
        }

        public Criteria andOperateinfoLike(String value) {
            addCriterion("operateInfo like", value, "operateinfo");
            return (Criteria) this;
        }

        public Criteria andOperateinfoNotLike(String value) {
            addCriterion("operateInfo not like", value, "operateinfo");
            return (Criteria) this;
        }

        public Criteria andOperateinfoIn(List<String> values) {
            addCriterion("operateInfo in", values, "operateinfo");
            return (Criteria) this;
        }

        public Criteria andOperateinfoNotIn(List<String> values) {
            addCriterion("operateInfo not in", values, "operateinfo");
            return (Criteria) this;
        }

        public Criteria andOperateinfoBetween(String value1, String value2) {
            addCriterion("operateInfo between", value1, value2, "operateinfo");
            return (Criteria) this;
        }

        public Criteria andOperateinfoNotBetween(String value1, String value2) {
            addCriterion("operateInfo not between", value1, value2, "operateinfo");
            return (Criteria) this;
        }

        public Criteria andTargetidIsNull() {
            addCriterion("targetID is null");
            return (Criteria) this;
        }

        public Criteria andTargetidIsNotNull() {
            addCriterion("targetID is not null");
            return (Criteria) this;
        }

        public Criteria andTargetidEqualTo(Integer value) {
            addCriterion("targetID =", value, "targetid");
            return (Criteria) this;
        }

        public Criteria andTargetidNotEqualTo(Integer value) {
            addCriterion("targetID <>", value, "targetid");
            return (Criteria) this;
        }

        public Criteria andTargetidGreaterThan(Integer value) {
            addCriterion("targetID >", value, "targetid");
            return (Criteria) this;
        }

        public Criteria andTargetidGreaterThanOrEqualTo(Integer value) {
            addCriterion("targetID >=", value, "targetid");
            return (Criteria) this;
        }

        public Criteria andTargetidLessThan(Integer value) {
            addCriterion("targetID <", value, "targetid");
            return (Criteria) this;
        }

        public Criteria andTargetidLessThanOrEqualTo(Integer value) {
            addCriterion("targetID <=", value, "targetid");
            return (Criteria) this;
        }

        public Criteria andTargetidIn(List<Integer> values) {
            addCriterion("targetID in", values, "targetid");
            return (Criteria) this;
        }

        public Criteria andTargetidNotIn(List<Integer> values) {
            addCriterion("targetID not in", values, "targetid");
            return (Criteria) this;
        }

        public Criteria andTargetidBetween(Integer value1, Integer value2) {
            addCriterion("targetID between", value1, value2, "targetid");
            return (Criteria) this;
        }

        public Criteria andTargetidNotBetween(Integer value1, Integer value2) {
            addCriterion("targetID not between", value1, value2, "targetid");
            return (Criteria) this;
        }

        public Criteria andOperatetimeIsNull() {
            addCriterion("operateTime is null");
            return (Criteria) this;
        }

        public Criteria andOperatetimeIsNotNull() {
            addCriterion("operateTime is not null");
            return (Criteria) this;
        }

        public Criteria andOperatetimeEqualTo(Date value) {
            addCriterion("operateTime =", value, "operatetime");
            return (Criteria) this;
        }

        public Criteria andOperatetimeNotEqualTo(Date value) {
            addCriterion("operateTime <>", value, "operatetime");
            return (Criteria) this;
        }

        public Criteria andOperatetimeGreaterThan(Date value) {
            addCriterion("operateTime >", value, "operatetime");
            return (Criteria) this;
        }

        public Criteria andOperatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("operateTime >=", value, "operatetime");
            return (Criteria) this;
        }

        public Criteria andOperatetimeLessThan(Date value) {
            addCriterion("operateTime <", value, "operatetime");
            return (Criteria) this;
        }

        public Criteria andOperatetimeLessThanOrEqualTo(Date value) {
            addCriterion("operateTime <=", value, "operatetime");
            return (Criteria) this;
        }

        public Criteria andOperatetimeIn(List<Date> values) {
            addCriterion("operateTime in", values, "operatetime");
            return (Criteria) this;
        }

        public Criteria andOperatetimeNotIn(List<Date> values) {
            addCriterion("operateTime not in", values, "operatetime");
            return (Criteria) this;
        }

        public Criteria andOperatetimeBetween(Date value1, Date value2) {
            addCriterion("operateTime between", value1, value2, "operatetime");
            return (Criteria) this;
        }

        public Criteria andOperatetimeNotBetween(Date value1, Date value2) {
            addCriterion("operateTime not between", value1, value2, "operatetime");
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