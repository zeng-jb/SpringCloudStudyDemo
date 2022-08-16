package com.zeng.jgitdemo.jgitService;



import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.RefSpec;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * The GitProcess
 *
 */
public class GitProcess {

    private static final Logger LOGGER = LoggerFactory.getLogger(GitProcess.class);

    protected void gitInit(String localRepoPath) {
        Repository newlyCreatedRepo = null;
        try {
            newlyCreatedRepo = FileRepositoryBuilder.create(new File(localRepoPath + "/.git"));
            newlyCreatedRepo.create();
            LOGGER.debug("Git init success");
        } catch (Exception e) {
            LOGGER.error("Git init fail.", e);
        } finally {
            if (newlyCreatedRepo != null) {
                newlyCreatedRepo.close();
            }
        }
    }

    /**
     * 克隆远程仓库
     *
     * @param remoteRepoPath:远端仓库url
     * @param branch：分支
     * @param userName：用户名
     * @param passWord：密码
     */
    protected void gitClone(String remoteRepoPath, String localRepoPath, String branch, String userName,
                            String passWord) {
        //设置远程服务器上的用户名和密码
        UsernamePasswordCredentialsProvider usernamePasswordCredentialsProvider
                = new UsernamePasswordCredentialsProvider(userName, passWord);
        //克隆代码库命令
        CloneCommand cloneCommand = Git.cloneRepository();
        Git git = null;
        try {
            git = cloneCommand.setURI(remoteRepoPath) //设置远程URI
                    .setBranch(branch) //设置clone下来的分支
                    .setDirectory(new File(localRepoPath)) //设置下载存放路径
                    .setCredentialsProvider(usernamePasswordCredentialsProvider) //设置权限验证
                    .call();
            LOGGER.debug("Git clone success");
        } catch (Exception e) {
            LOGGER.error("Git clone fail.", e);
        } finally {
            if (git != null) {
                git.close();
            }
        }
    }

    /**
     * 添加文件
     *
     * @param addFilePath:添加文件路径
     * @param localRepoPath：分支
     * @return boolean:结果
     */
    protected boolean gitAdd(String addFilePath, String localRepoPath) {
        boolean addFileFlag = true;
        Git git = null;
        try {
            git = Git.open(new File(localRepoPath + "/.git"));
            //add file to git
            git.add().addFilepattern(addFilePath).call();
        } catch (Exception e) {
            addFileFlag = false;
        } finally {
            if (git != null) {
                git.close();
            }
        }
        return addFileFlag;
    }

    /**
     * 本地代码提交
     *
     * @param msg:提交信息
     * @param localRepoPath:本地代码仓位置
     */
    protected void gitCommit(String msg, String localRepoPath) {
        Git git = null;
        try {
            git = new Git(new FileRepository(localRepoPath + "/.git"));
            //全部提交
            git.commit().setAll(true).setMessage(msg).call();
            LOGGER.debug("Git commit success");
        } catch (Exception e) {
            LOGGER.error("Git commit fail.", e);
        } finally {
            if (git != null) {
                git.close();
            }
        }
    }

    /**
     * 本地代码提交
     *
     * @param branch:提交信息
     * @param localRepoPath:提交信息
     * @return boolean:提交结果
     */
    protected boolean gitPull(String branch, String localRepoPath) {
        boolean pullFlag = true;
        try (Git git = Git.open(new File(localRepoPath + "/.git"))) {
            git.pull().setRemoteBranchName(branch).call();
        } catch (Exception e) {
            pullFlag = false;
        }
        return pullFlag;
    }

    /**
     * push本地代码到远程仓库
     *
     * @param branch：分支
     * @param userName：用户名
     * @param passWord：密码
     */
    protected void gitPush(String remoteRepoPath, String localRepoPath, String branch, String userName,
                           String passWord) {
        Git git = null;
        try {
            git = new Git(new FileRepository(localRepoPath + "/.git"));
            UsernamePasswordCredentialsProvider usernamePasswordCredentialsProvider
                    = new UsernamePasswordCredentialsProvider(userName, passWord);
            git.push()
                    .setRemote(remoteRepoPath)
                    .setRefSpecs(new RefSpec(branch))
                    .setCredentialsProvider(usernamePasswordCredentialsProvider)
                    .call();
            LOGGER.debug("Git push success");
        } catch (Exception e) {
            LOGGER.error("Git push fail.", e);
        } finally {
            if (git != null) {
                git.close();
            }
        }
    }


}