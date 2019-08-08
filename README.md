# mybatis generator (MBG)的用法
[MyBatis Generator 配置文件详解](https://www.jianshu.com/p/2cace13b7819)

###Mybatis generator 踩坑
- 重复使用generator生成mapper.xml时，mapper.xml的  
文件并不是每次都覆盖原文件，而是在源文件中追加新内容  
导致项目报错  
--原因：MBG依赖版本过低  
--解决办法：使用最新的依赖版本,在generatorConfig.xml文件中添加  
    生成mapper.xml时覆盖原文件的配置
  ```maven
  <plugin type="org.mybatis.generator.plugins.UnmergeableXmlMappersPlugin"/>
  ```