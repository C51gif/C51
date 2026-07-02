import com.mybatisflex.codegen.Generator;
import com.mybatisflex.codegen.config.EntityConfig;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.zaxxer.hikari.HikariDataSource;


public class CodeGen {
    //项目路径
    static String parent = System.getProperty("user.dir");
    //子项目路径
    static String project = "custom";
    //表名
    static String table = "bed_num";
    public static void main(String[] args) {
        HikariDataSource hikari = new HikariDataSource();
        hikari.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikari.setJdbcUrl("jdbc:mysql://192.168.79.128:3310/test?useUnicode=true&characterEncoding=utf8");
        hikari.setUsername("root");
        hikari.setPassword("050607");
        GlobalConfig globalConfig = getConfig();
        Generator generator = new Generator(hikari, globalConfig);
        generator.generate();
    }

    private static GlobalConfig getConfig() {
        //创建对象
        GlobalConfig globalConfig = new GlobalConfig();
        //entity
        EntityConfig entityConfig = globalConfig.enableEntity();

        //jdk
        entityConfig.setJdkVersion(21);
        //lombok
        entityConfig.setWithLombok(true);
        //entity路径
        entityConfig.setSourceDir(String.format("%s/entity/src/main/java", parent));
        //xml路径
        globalConfig.enableMapperXml();
        globalConfig.setMapperXmlPath(String.format("%s/%s/src/main/resources/com/neuedu/mapper", parent,project));
        //mapper
        globalConfig.enableMapper();
        //service
        globalConfig.enableService();
        globalConfig.enableServiceImpl();
        globalConfig.enableController();

        //其他文件
        globalConfig.setSourceDir(String.format("%s/%s/src/main/java", parent,project));
        //包名
        globalConfig.setBasePackage("com.neuedu");
        //生成的表
        globalConfig.setGenerateTable(table);
        return globalConfig;


    }

}
