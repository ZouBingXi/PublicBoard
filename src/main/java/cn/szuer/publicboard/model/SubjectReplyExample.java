package cn.szuer.publicboard.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SubjectReplyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SubjectReplyExample() {
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

        public Criteria andReplyidIsNull() {
            addCriterion("replyID is null");
            return (Criteria) this;
        }

        public Criteria andReplyidIsNotNull() {
            addCriterion("replyID is not null");
            return (Criteria) this;
        }

        public Criteria andReplyidEqualTo(Integer value) {
            addCriterion("replyID =", value, "replyid");
            return (Criteria) this;
        }

        public Criteria andReplyidNotEqualTo(Integer value) {
            addCriterion("replyID <>", value, "replyid");
            return (Criteria) this;
        }

        public Criteria andReplyidGreaterThan(Integer value) {
            addCriterion("replyID >", value, "replyid");
            return (Criteria) this;
        }

        public Criteria andReplyidGreaterThanOrEqualTo(Integer value) {
            addCriterion("replyID >=", value, "replyid");
            return (Criteria) this;
        }

        public Criteria andReplyidLessThan(Integer value) {
            addCriterion("replyID <", value, "replyid");
            return (Criteria) this;
        }

        public Criteria andReplyidLessThanOrEqualTo(Integer value) {
            addCriterion("replyID <=", value, "replyid");
            return (Criteria) this;
        }

        public Criteria andReplyidIn(List<Integer> values) {
            addCriterion("replyID in", values, "replyid");
            return (Criteria) this;
        }

        public Criteria andReplyidNotIn(List<Integer> values) {
            addCriterion("replyID not in", values, "replyid");
            return (Criteria) this;
        }

        public Criteria andReplyidBetween(Integer value1, Integer value2) {
            addCriterion("replyID between", value1, value2, "replyid");
            return (Criteria) this;
        }

        public Criteria andReplyidNotBetween(Integer value1, Integer value2) {
            addCriterion("replyID not between", value1, value2, "replyid");
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

        public Criteria andCommentidIsNull() {
            addCriterion("commentID is null");
            return (Criteria) this;
        }

        public Criteria andCommentidIsNotNull() {
            addCriterion("commentID is not null");
            return (Criteria) this;
        }

        public Criteria andCommentidEqualTo(Integer value) {
            addCriterion("commentID =", value, "commentid");
            return (Criteria) this;
        }

        public Criteria andCommentidNotEqualTo(Integer value) {
            addCriterion("commentID <>", value, "commentid");
            return (Criteria) this;
        }

        public Criteria andCommentidGreaterThan(Integer value) {
            addCriterion("commentID >", value, "commentid");
            return (Criteria) this;
        }

        public Criteria andCommentidGreaterThanOrEqualTo(Integer value) {
            addCriterion("commentID >=", value, "commentid");
            return (Criteria) this;
        }

        public Criteria andCommentidLessThan(Integer value) {
            addCriterion("commentID <", value, "commentid");
            return (Criteria) this;
        }

        public Criteria andCommentidLessThanOrEqualTo(Integer value) {
            addCriterion("commentID <=", value, "commentid");
            return (Criteria) this;
        }

        public Criteria andCommentidIn(List<Integer> values) {
            addCriterion("commentID in", values, "commentid");
            return (Criteria) this;
        }

        public Criteria andCommentidNotIn(List<Integer> values) {
            addCriterion("commentID not in", values, "commentid");
            return (Criteria) this;
        }

        public Criteria andCommentidBetween(Integer value1, Integer value2) {
            addCriterion("commentID between", value1, value2, "commentid");
            return (Criteria) this;
        }

        public Criteria andCommentidNotBetween(Integer value1, Integer value2) {
            addCriterion("commentID not between", value1, value2, "commentid");
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

        public Criteria andCommenttimeIsNull() {
            addCriterion("commentTime is null");
            return (Criteria) this;
        }

        public Criteria andCommenttimeIsNotNull() {
            addCriterion("commentTime is not null");
            return (Criteria) this;
        }

        public Criteria andCommenttimeEqualTo(Date value) {
            addCriterion("commentTime =", value, "commenttime");
            return (Criteria) this;
        }

        public Criteria andCommenttimeNotEqualTo(Date value) {
            addCriterion("commentTime <>", value, "commenttime");
            return (Criteria) this;
        }

        public Criteria andCommenttimeGreaterThan(Date value) {
            addCriterion("commentTime >", value, "commenttime");
            return (Criteria) this;
        }

        public Criteria andCommenttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("commentTime >=", value, "commenttime");
            return (Criteria) this;
        }

        public Criteria andCommenttimeLessThan(Date value) {
            addCriterion("commentTime <", value, "commenttime");
            return (Criteria) this;
        }

        public Criteria andCommenttimeLessThanOrEqualTo(Date value) {
            addCriterion("commentTime <=", value, "commenttime");
            return (Criteria) this;
        }

        public Criteria andCommenttimeIn(List<Date> values) {
            addCriterion("commentTime in", values, "commenttime");
            return (Criteria) this;
        }

        public Criteria andCommenttimeNotIn(List<Date> values) {
            addCriterion("commentTime not in", values, "commenttime");
            return (Criteria) this;
        }

        public Criteria andCommenttimeBetween(Date value1, Date value2) {
            addCriterion("commentTime between", value1, value2, "commenttime");
            return (Criteria) this;
        }

        public Criteria andCommenttimeNotBetween(Date value1, Date value2) {
            addCriterion("commentTime not between", value1, value2, "commenttime");
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