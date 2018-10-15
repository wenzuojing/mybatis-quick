package com.github.wens.entity;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.github.wens.mybatis.annotations.TableField;
import com.github.wens.mybatis.annotations.TableId;
import com.github.wens.mybatis.annotations.TableName;
import com.github.wens.mybatis.examples.Example;

/**
 *
 * 
 *
 */
@TableName(value = "test_tb")
public class TestTb implements Serializable {

	/**  */
	@TableId
	private Integer id;

	/**  */
	@TableField(value = "tinyint_field")
	private Integer tinyintField;

	/**  */
	@TableField(value = "smallint_field")
	private Integer smallintField;

	/**  */
	@TableField(value = "mediumint_field")
	private Integer mediumintField;

	/**  */
	@TableField(value = "int_field")
	private Integer intField;

	/**  */
	@TableField(value = "biginit_field")
	private Long biginitField;

	/**  */
	@TableField(value = "fload_field")
	private Float floadField;

	/**  */
	@TableField(value = "double_field")
	private Double doubleField;

	/**  */
	@TableField(value = "decimal_field")
	private BigDecimal decimalField;

	/**  */
	@TableField(value = "bit_field")
	private Boolean bitField;

	/**  */
	@TableField(value = "char_field")
	private String charField;

	/**  */
	@TableField(value = "varchar_field")
	private String varcharField;

	/**  */
	@TableField(value = "tinytext_field")
	private String tinytextField;

	/**  */
	@TableField(value = "mediumtext_field")
	private String mediumtextField;

	/**  */
	@TableField(value = "longtext_field")
	private String longtextField;

	/**  */
	@TableField(value = "tinyblob_field")
	private byte[] tinyblobField;

	/**  */
	@TableField(value = "mediumblob_field")
	private byte[] mediumblobField;

	/**  */
	@TableField(value = "blob_field")
	private byte[] blobField;

	/**  */
	@TableField(value = "longblob_field")
	private byte[] longblobField;

	/**  */
	@TableField(value = "binary_field")
	private Object binaryField;

	/**  */
	@TableField(value = "varbinary_field")
	private Object varbinaryField;

	/**  */
	@TableField(value = "enum_field")
	private Object enumField;

	/**  */
	@TableField(value = "set_field")
	private Object setField;

	/**  */
	@TableField(value = "date_field")
	private Date dateField;

	/**  */
	@TableField(value = "datetime_field")
	private Date datetimeField;

	/**  */
	@TableField(value = "timestamp_field")
	private Date timestampField;

	/**  */
	@TableField(value = "time_field")
	private Object timeField;

	/**  */
	@TableField(value = "year_field")
	private Object yearField;

	/**  */
	@TableField(value = "json_field")
	private Object jsonField;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTinyintField() {
		return this.tinyintField;
	}

	public void setTinyintField(Integer tinyintField) {
		this.tinyintField = tinyintField;
	}

	public Integer getSmallintField() {
		return this.smallintField;
	}

	public void setSmallintField(Integer smallintField) {
		this.smallintField = smallintField;
	}

	public Integer getMediumintField() {
		return this.mediumintField;
	}

	public void setMediumintField(Integer mediumintField) {
		this.mediumintField = mediumintField;
	}

	public Integer getIntField() {
		return this.intField;
	}

	public void setIntField(Integer intField) {
		this.intField = intField;
	}

	public Long getBiginitField() {
		return this.biginitField;
	}

	public void setBiginitField(Long biginitField) {
		this.biginitField = biginitField;
	}

	public Float getFloadField() {
		return this.floadField;
	}

	public void setFloadField(Float floadField) {
		this.floadField = floadField;
	}

	public Double getDoubleField() {
		return this.doubleField;
	}

	public void setDoubleField(Double doubleField) {
		this.doubleField = doubleField;
	}

	public BigDecimal getDecimalField() {
		return this.decimalField;
	}

	public void setDecimalField(BigDecimal decimalField) {
		this.decimalField = decimalField;
	}

	public Boolean getBitField() {
		return this.bitField;
	}

	public void setBitField(Boolean bitField) {
		this.bitField = bitField;
	}

	public String getCharField() {
		return this.charField;
	}

	public void setCharField(String charField) {
		this.charField = charField;
	}

	public String getVarcharField() {
		return this.varcharField;
	}

	public void setVarcharField(String varcharField) {
		this.varcharField = varcharField;
	}

	public String getTinytextField() {
		return this.tinytextField;
	}

	public void setTinytextField(String tinytextField) {
		this.tinytextField = tinytextField;
	}

	public String getMediumtextField() {
		return this.mediumtextField;
	}

	public void setMediumtextField(String mediumtextField) {
		this.mediumtextField = mediumtextField;
	}

	public String getLongtextField() {
		return this.longtextField;
	}

	public void setLongtextField(String longtextField) {
		this.longtextField = longtextField;
	}

	public byte[] getTinyblobField() {
		return this.tinyblobField;
	}

	public void setTinyblobField(byte[] tinyblobField) {
		this.tinyblobField = tinyblobField;
	}

	public byte[] getMediumblobField() {
		return this.mediumblobField;
	}

	public void setMediumblobField(byte[] mediumblobField) {
		this.mediumblobField = mediumblobField;
	}

	public byte[] getBlobField() {
		return this.blobField;
	}

	public void setBlobField(byte[] blobField) {
		this.blobField = blobField;
	}

	public byte[] getLongblobField() {
		return this.longblobField;
	}

	public void setLongblobField(byte[] longblobField) {
		this.longblobField = longblobField;
	}

	public Object getBinaryField() {
		return this.binaryField;
	}

	public void setBinaryField(Object binaryField) {
		this.binaryField = binaryField;
	}

	public Object getVarbinaryField() {
		return this.varbinaryField;
	}

	public void setVarbinaryField(Object varbinaryField) {
		this.varbinaryField = varbinaryField;
	}

	public Object getEnumField() {
		return this.enumField;
	}

	public void setEnumField(Object enumField) {
		this.enumField = enumField;
	}

	public Object getSetField() {
		return this.setField;
	}

	public void setSetField(Object setField) {
		this.setField = setField;
	}

	public Date getDateField() {
		return this.dateField;
	}

	public void setDateField(Date dateField) {
		this.dateField = dateField;
	}

	public Date getDatetimeField() {
		return this.datetimeField;
	}

	public void setDatetimeField(Date datetimeField) {
		this.datetimeField = datetimeField;
	}

	public Date getTimestampField() {
		return this.timestampField;
	}

	public void setTimestampField(Date timestampField) {
		this.timestampField = timestampField;
	}

	public Object getTimeField() {
		return this.timeField;
	}

	public void setTimeField(Object timeField) {
		this.timeField = timeField;
	}

	public Object getYearField() {
		return this.yearField;
	}

	public void setYearField(Object yearField) {
		this.yearField = yearField;
	}

	public Object getJsonField() {
		return this.jsonField;
	}

	public void setJsonField(Object jsonField) {
		this.jsonField = jsonField;
	}

}
