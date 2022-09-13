package com.wjm.blog.security.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.jdbc.object.SqlQuery;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig.Builder;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * ================================================================
 * 说明：mybatisPlus逆向工程工具类- 多数据源
 * 
 * 作者 时间 注释 王江民 2019-09-25 创建
 * 
 * 1778682202@qq.com
 * ==================================================================
 */
public class MybatisPlusUtil {

	List<String> urls = new ArrayList<>();
	List<String> usernames = new ArrayList<>();
	List<String> passwords = new ArrayList<>();
	List<String> tables = new ArrayList<>();
	
	String moduleName;
	List<String> dataSource = new ArrayList<>();
	String packageParent;
	String packageMapping;
	String yamlParent;
	String outputDir;
	
	List<Builder> DATA_SOURCE_CONFIG = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		System.out.println("开始");

		new MybatisPlusUtil()
				.setYamlParent("spring.datasource.druid")			//	数据源配置父节点
				.setModuleName("security")							//	模块名
				.setDataSource("db1")								//	数据源名称,可以多个
				.setPackageParent("com.wjm.blog.security.pojo")		//	包路径
				.setPackageMapping("/src/main/resources/mapping/")	//	mapping 路径
				.loadYaml("application-dev.yml")					//	配置文件路径
				.initDataSource("db/h2/init.sql")					//	需要执行的sql
				.tables("user")										//	需要生成的表,可以多个
				.check()
				.refreshPojo();

//		mpu.refreshPojo(
//				"jdbc:mysql://172.18.17.154:13306/inmgn?useAffectedRows=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true", 
//				"InMgn",
//				"Mw2ESeK4TjZFeM5E");

	}

	MybatisPlusUtil check() {
		System.out.println("\n\n**********		检查配置		**********");
		System.out.println("moduleName: " + moduleName);
		for (int i = 0; i < dataSource.size() ;i++){
		System.out.println("\ndataSource: " + dataSource.get(i));
		System.out.println("url:" + urls.get(i));
		System.out.println("username: " + usernames.get(i));
		System.out.println("password: " + passwords.get(i));
		}
		System.out.println("\ntables: " + tables);
		System.out.println("packageParent: " + packageParent);
		System.out.println("packageMapping: " + packageMapping);
		System.out.println("yamlParent: " + yamlParent);
		if (outputDir == null ) {setOutputDir();}
		System.out.println("outputDir: " + outputDir);
		System.out.println("**********		检查配置		**********\n\n");
		return this;
	}

	MybatisPlusUtil setModuleName(String moduleName){
		this.moduleName = moduleName;
		return this;
	}

	MybatisPlusUtil setDataSource(String... dataSource){
		this.dataSource = Arrays.asList(dataSource);
		return this;
	}

	MybatisPlusUtil setPackageParent(String packageParent){
		this.packageParent = packageParent;
		return this;
	}

	MybatisPlusUtil setPackageMapping(String packageMapping){
		this.packageMapping = packageMapping;
		return this;
	}

	MybatisPlusUtil setYamlParent(String yamlParent){
		this.yamlParent = yamlParent;
		return this;
	}

	MybatisPlusUtil setOutputDir(){
		return setOutputDir(Paths.get(System.getProperty("user.dir"),moduleName == null ? null: moduleName).toString());
	}
	MybatisPlusUtil setOutputDir(String path){
		this.outputDir = path;
		return this;
	}
	/**
	 * 
	 * @Title: create
	 * @Description: 创建连接对象
	 * @return 
	 * @return: Builder
	 * @author 王江民		@date 2022-07-11 09:34:09	@version V1.0
	 */
	protected void create() {
		for(int i = 0 ; i < dataSource.size() ; i++){
			DATA_SOURCE_CONFIG.add(new Builder(urls.get(i), usernames.get(i), passwords.get(i)));
		}
	}
	
	 /**
     * 执行初始化数据库脚本
     */
	MybatisPlusUtil initDataSource(String path) throws Exception {
		create();
		if(DATA_SOURCE_CONFIG.isEmpty()){
			throw new Exception("DATA_SOURCE_CONFIG 数据源为空");
		}
		for(int i = 0;i<DATA_SOURCE_CONFIG.size();i++){
        Connection conn = DATA_SOURCE_CONFIG.get(i).build().getConn();
        InputStream inputStream = MybatisPlusUtil.class.getClassLoader().getResourceAsStream(path);
        ScriptRunner scriptRunner = new ScriptRunner(conn);
        scriptRunner.setAutoCommit(true);
        scriptRunner.runScript(new InputStreamReader(inputStream));
        conn.close();
		}

        return this;
    }
	
	/**
	 * 
	 * @Title: loadYaml
	 * @Description: 加载 yaml 文件
	 * @param yamlName
	 * @return 
	 * @return: MybatisPlusUtil
	 * @author 王江民		@date 2022-07-11 09:40:44	@version V1.0
	 */
	MybatisPlusUtil loadYaml(String yamlName) throws Exception {
		if(yamlParent == null){
			throw new Exception("yamlParent 配置文件父节点参数不存在!");
		}
		if(dataSource.isEmpty()){
			throw new Exception("dataSource 数据库名称不存在!");
		}
		yamlParent += ".";
		YamlUtil yamlUtil = new YamlUtil(yamlName);
		for (int i =0;i<dataSource.size();i++){
			String dbName = dataSource.get(i);
			urls.add(yamlUtil.getValueByKey(yamlParent + dbName + ".url"));
			usernames.add(yamlUtil.getValueByKey(yamlParent + dbName + ".username"));
			passwords.add(yamlUtil.getValueByKey(yamlParent + dbName + ".password"));
		}


		return this;
	}
	
	public MybatisPlusUtil tables(String... tables) {
		this.tables = Arrays.asList(tables);
		return this;
	}

	public void refreshPojo(String url, String username, String password) {
		DATA_SOURCE_CONFIG.add(new Builder(url, username, password));
		refreshPojo();
	}

	public void refreshPojo() {
		for(int i = 0;i<dataSource.size();i++){
			refreshPojo(dataSource.get(i),DATA_SOURCE_CONFIG.get(i));
		}
	}

	public void refreshPojo(String dataSourceName,Builder builder) {
		System.out.println("开始构建...");
		String parent = packageParent +"." + dataSourceName + ".auto";
		String classOutPutDir = Paths.get(outputDir,"src", "main", "java").toString();
		String mappingOutPutDir = Paths.get(outputDir,packageMapping,dataSourceName).toString();
		System.out.println("实体类 输出路径: " + classOutPutDir + " " + parent);
		System.out.println("mapping 输出路径: " + mappingOutPutDir);

		FastAutoGenerator.create(builder)
//							.create(url, username, password)
			.globalConfig(b -> {
			b.author("王江民")
			.enableSwagger()
			.fileOverride()
//			.outputDir(filePath + "/src/main/java")
			.outputDir(classOutPutDir)
//			.disableOpenDir()		//	不打开生成目录

			.fileOverride() 		// 	覆盖
			;
		}).packageConfig(b -> {
			b.parent(parent)
//			builder.parent("com.ahead.ebook.autoBuilder.h2")
//					.entity("pojo")
//					.controller("controller")
//					.service("service")
//					.serviceImpl("serviceImpl")
//					.mapper("mapper")
//					.moduleName("")
					.pathInfo(Collections.singletonMap(OutputFile.mapperXml,mappingOutPutDir))
			;
		}).strategyConfig(b -> {
	        
	        String db = getMethodName(dataSourceName);
	        
			b.addInclude(tables)
//					.addTablePrefix("")		//	表前缀过滤
//					.addTableSuffix("")		//	表后缀过滤
//					.addExclude("")			//	排除的表
//					.addFieldPrefix("")		//	字段前缀过滤
//					.addFieldSuffix("")		//	字段后缀过滤
					.entityBuilder()
					.formatFileName("%s" + db)
					.enableLombok()
					.enableActiveRecord()
//					.superClass()			//	设置父类
					.naming(NamingStrategy.underline_to_camel) // 下划线转驼峰
					.columnNaming(NamingStrategy.underline_to_camel) // 下划线转驼峰
					.mapperBuilder()
					.formatMapperFileName("%sAuto" + db + "Mapper")
					.formatXmlFileName("%sAuto" + db + "Mapper")
					.serviceBuilder()
					.formatServiceFileName("%sAuto" + db + "Service")
					.formatServiceImplFileName("%sAuto" + db + "ServiceImpl")
					;

		})
		.templateEngine(new FreemarkerTemplateEngine())
		.templateConfig(b -> {
			b.disable(TemplateType.CONTROLLER)		//	不生成 controller
			;
		})
		.execute()
		;
		System.out.println("完成...");
	}
	
	/**
     * 首字母大写(进行字母的ascii编码前移，效率是最高的)
     *
     * @param fieldName 需要转化的字符串
     */
    public static String getMethodName(String fieldName){
        char[] chars = fieldName.toCharArray();
        chars[0] = toUpperCase(chars[0]);
        return String.valueOf(chars);
    }
 
 
    /**
     * 字符转成大写
     *
     * @param c 需要转化的字符
     */
    public static char toUpperCase(char c) {
        if (97 <= c && c <= 122) {
            c ^= 32;
        }
        return c;
    }

}
