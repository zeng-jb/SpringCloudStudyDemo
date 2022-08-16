package com.zeng.jgitdemo.jgitService;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

public class Main {
    public static void main(String[] args) {
        JGitUtils jGitUtils = new JGitUtils();
//        String localPath = "E:\\project_rep";    //本地仓库位置
//        Git git = jGitUtils.openRpo(localPath); //获取git对象
//        System.out.println(git);

        String localPath = "E:\\project_rep";    //本地仓库位置
        String remotePath = "https://gitee.com/zeng-jiabin/gkzy_recommend.git";  //远端仓库URL
        String branch = "master";   //克隆目标分支
        String userName = "1573439264@qq.com";  //远端仓库用户名
        String password = "zeng19980715";  //远端仓库密码密码
        try{
            jGitUtils.gitClone(userName,password,localPath,remotePath,branch);
        }catch (GitAPIException e){
            e.printStackTrace();
        }
    }
}
