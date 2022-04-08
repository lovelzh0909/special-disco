// package com.example.demo;
//
// import com.baomidou.mybatisplus.annotation.FieldFill;
// import com.baomidou.mybatisplus.annotation.IdType;
// import com.baomidou.mybatisplus.generator.FastAutoGenerator;
// import com.baomidou.mybatisplus.generator.config.*;
// import com.baomidou.mybatisplus.generator.fill.Column;
//
// import java.util.Collections;
//
// public class GeneratorApplication {
// 	private static final String projectPath = System.getProperty("user.dir");//获取项目路径
// 	private static final String url = "jdbc:mysql://47.103.94.131:3306/demo?serverTimezone=GMT%2B8";//jdbc 路径
// 	private static final String username = "demo";//数据库账号
// 	private static final String password = "123456";//数据库密码
// 	private static final String parentPackageName = "com.example";// 设置父包名
// 	private static final String moduleName = "demo";// 设置父包模块名
// 	//private static final String writer = "xgj";// 设置作者
// 	private static final String outPath = projectPath + "\\src\\main\\java\\";//输出路径
// 	private static final String mapperPath = projectPath + "\\src\\main\\resources\\mybatis\\mappers";// 设置mapperXml 模板路径
//
// 	public static void main(String[] args) {
// 		String[] tableNames = {"questionrelscore"};//可生成多个表
// 		GeneratorApplication.execute(tableNames);
// 	}
//
// 	public static void execute(String[] tableNames) {
// 		FastAutoGenerator.create(url, username, password)
// 				.globalConfig(builder -> {
// 					builder//.author(writer) // 设置作者
// 							.enableSwagger() // 开启 swagger 模式
// 							.fileOverride() // 覆盖已生成文件
// 							.outputDir(outPath) // 指定输出目录
// 							.disableOpenDir();//禁止打开输出目录
// 				})
// 				.packageConfig(builder -> {
// 					builder.parent(parentPackageName) // 设置父包名
// 							.moduleName(moduleName) // 设置父包模块名
//                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml,mapperPath)); // 设置mapperXml生成路径
//
// 				})
//
// 				.strategyConfig(builder -> {
// 					builder.addInclude(tableNames)// 设置需要生成的表名
// 							.addTablePrefix("t_") // 设置过滤表前缀
// 							//Service 策略配置
// 							.serviceBuilder()
// 							.formatServiceFileName("%sService")//格式化 service 接口文件名称
// 							.formatServiceImplFileName("%sServiceImpl")//格式化 service 实现类文件名称
// 							//Entity 策略配置
// 							.entityBuilder()
// 							.enableChainModel()//开启链式模型
// 							.enableLombok()//开启Lombok模型
// 							.enableTableFieldAnnotation()//开启生成实体时生成字段注解
// 							.logicDeleteColumnName("deleted")//默认删除属性名称（数据库）
// 							.logicDeletePropertyName("deleted")//默认删除属性名称（实体）
// 							.versionColumnName("version")//乐观锁属性名（数据库）
// 							.versionPropertyName("version")//乐观锁属性名（实体）
// 							.addTableFills(new Column("create_Time", FieldFill.INSERT))//添加表字段填充(自动填充)
// 							.addTableFills(new Column("update_Time", FieldFill.INSERT_UPDATE))//添加表字段填充（自动填充）
// 							//controller 策略配置
// 							.controllerBuilder()
// 							.enableRestStyle();//开启生成@RestController 控制器
// 				})
// //                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
// 				.execute();
// 	}
// }