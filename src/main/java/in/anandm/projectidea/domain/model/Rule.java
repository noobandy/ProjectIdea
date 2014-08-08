package in.anandm.projectidea.domain.model;

import com.googlecode.genericdao.search.Filter;

public class Rule {
	/** The field. */
	private String field;

	/** The op. */
	private String op;

	/** The data. */
	private String data;

	/**
	 * Instantiates a new rule.
	 */
	public Rule() {

	}

	/**
	 * Instantiates a new rule.
	 * 
	 * @param field
	 *            the field
	 * @param op
	 *            the op
	 * @param data
	 *            the data
	 */
	public Rule(String field, String op, String data) {
		this.field = field;
		this.op = op;
		this.data = data;
	}

	/**
	 * Gets the field.
	 * 
	 * @return the field
	 */
	public String getField() {
		return field;
	}

	/**
	 * Sets the field.
	 * 
	 * @param field
	 *            the new field
	 */
	public void setField(String field) {
		this.field = field;
	}

	/**
	 * Gets the op.
	 * 
	 * @return the op
	 */
	public String getOp() {
		return op;
	}

	/**
	 * Sets the op.
	 * 
	 * @param op
	 *            the new op
	 */
	public void setOp(String op) {
		this.op = op;
	}

	/**
	 * Gets the data.
	 * 
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * Sets the data.
	 * 
	 * @param data
	 *            the new data
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * Gets the mapped operator.
	 * 
	 * @return the mapped operator
	 */
	public int getMappedOperator() {
		if (this.op.equalsIgnoreCase("eq")) {
			return Filter.OP_EQUAL;
		} else if (this.op.equalsIgnoreCase("ne")) {
			return Filter.OP_NOT_EQUAL;
		} else if (this.op.equalsIgnoreCase("lt")) {
			return Filter.OP_LESS_THAN;
		} else if (this.op.equalsIgnoreCase("le")) {
			return Filter.OP_LESS_OR_EQUAL;
		} else if (this.op.equalsIgnoreCase("gt")) {
			return Filter.OP_GREATER_THAN;
		} else if (this.op.equalsIgnoreCase("ge")) {
			return Filter.OP_GREATER_OR_EQUAL;
		} else if (this.op.equalsIgnoreCase("bw")) {
			return Filter.OP_LIKE;
		} else if (this.op.equalsIgnoreCase("in")) {
			return Filter.OP_IN;
		} else if (this.op.equalsIgnoreCase("ni")) {
			return Filter.OP_NOT_IN;
		} else if (this.op.equalsIgnoreCase("ew")) {
			return Filter.OP_LIKE;
		} else if (this.op.equalsIgnoreCase("cn")) {
			return Filter.OP_LIKE;
		} else {
			return -1;
		}

	}
}
