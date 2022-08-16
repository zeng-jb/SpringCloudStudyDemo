package com.zeng.jgitdemo.jgitService;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class JGitUtils {
    public static Git openRpo(String dir){
        Git git = null;
        try {
            Repository repository = new FileRepositoryBuilder()
                    .setGitDir(Paths.get(dir, ".git").toFile())
                    .build();
            git = new Git(repository);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return git;
    }

    public void gitClone(String userName,String password,String localPath,String remotePath,String branch) throws GitAPIException {
        //克隆
        CredentialsProvider provider = new UsernamePasswordCredentialsProvider(userName, password);  //生成身份信息
        File localpath = new File(localPath);
        Git git = Git.cloneRepository()
                .setURI(remotePath)   //设置git远端URL
                .setDirectory(localpath)  //设置本地仓库位置
                .setCredentialsProvider(provider)   //设置身份验证
                .setCloneSubmodules(true)    //设置是否克隆子仓库
                .setBranch(branch)  //设置克隆分支
                .call();   //启动命令
        git.getRepository().close();
        git.close();     //关闭源，以释放本地仓库锁

    }

}


