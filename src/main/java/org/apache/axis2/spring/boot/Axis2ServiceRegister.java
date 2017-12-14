package org.apache.axis2.spring.boot;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuebo on 2017/12/14.
 */
@Component
public class  Axis2ServiceRegister {
    private static Logger logger= LoggerFactory.getLogger(Axis2ServiceRegister.class);
    private File repo=new File(System.getProperty("java.io.tmpdir"),"axis2repo");
    private List<Axis2ServiceInfo> resources=new ArrayList<Axis2ServiceInfo>();

    @PostConstruct
    public void init(){
        logger.info("Axis2ServiceRegister init called");
        if(!repo.exists()){
            repo.mkdirs();
        }
        loadServices();
    }

    private void loadServices() {
        try {
            registerServiceXml(new ClassPathResource("services.xml"),"test");
            registerServiceAar(new ClassPathResource("version-1.7.7.aar"),"version");
        } catch (IOException e) {
            logger.error("error when load service: {}",e);
        }
    }

    @PreDestroy
    public void cleanup() throws IOException {
        logger.info("Axis2ServiceRegister cleanup called");
        if(repo.exists()){
            FileUtils.deleteDirectory(repo);
        }
    }

    public String getRepoPath(){
        return repo.getAbsolutePath();
    }

    public void registerServiceXml(Resource resource,String serviceName) throws IOException{
        Axis2ServiceInfo info=new Axis2ServiceInfo();

        FileOutputStream fileOutputStream=null;
        try{
            File serviceDir=new File(repo,"services/"+serviceName+"/META-INF");
            serviceDir.mkdirs();
            fileOutputStream=new FileOutputStream(new File(serviceDir,"services.xml"));
            IOUtils.copy(resource.getInputStream(),fileOutputStream);
        }finally {
            IOUtils.closeQuietly(fileOutputStream);
        }
        info.setName(serviceName);
        info.setResource(resource);
        resources.add(info);
    }
    public void registerServiceAar(Resource resource,String serviceName) throws IOException{
        Axis2ServiceInfo info=new Axis2ServiceInfo();
        FileOutputStream fileOutputStream=null;
        try{
            File serviceDir=new File(repo,"services");
            serviceDir.mkdirs();
            fileOutputStream=new FileOutputStream(new File(serviceDir,resource.getFilename()));
            IOUtils.copy(resource.getInputStream(),fileOutputStream);
        }finally {
            IOUtils.closeQuietly(fileOutputStream);
        }
        info.setName(serviceName);
        info.setResource(resource);
        resources.add(info);
    }

    public void registerModuleMar(Resource resource,String serviceName)throws IOException{
        Axis2ServiceInfo info=new Axis2ServiceInfo();
        FileOutputStream fileOutputStream=null;
        try{
            File serviceDir=new File(repo,"modules");
            serviceDir.mkdirs();
            fileOutputStream=new FileOutputStream(new File(serviceDir,resource.getFilename()));
            IOUtils.copy(resource.getInputStream(),fileOutputStream);
        }finally {
            IOUtils.closeQuietly(fileOutputStream);
        }
        info.setName(serviceName);
        info.setResource(resource);
        resources.add(info);
    }

}
