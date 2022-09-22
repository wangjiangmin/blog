/**
 * @Title: DS.java
 * @Package com.ahead.ebook.config.db
 * @Description: 切换数据库注解
 * @author 王江民		@date 2022-07-01 11:26:58	@version V1.0 
 */

package com.wjm.blog.security.config.db;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: DS
 * @Description: 切换数据库注解
 * @author 王江民		@date 2022-07-01 11:26:58	@version V1.0 
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DS {
	
	DataSources value();
	
//	String value() default DatasourceNames.H2;
}
