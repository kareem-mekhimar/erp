/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.controllers.hr;

import com.bridge.entities.hr.Job;
import com.bridge.services.hr.JobService;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author AIA
 */
@Named
@ViewScoped
public class JobController implements Serializable {

    @EJB
    private JobService jobservice;
    private Job job;
    private List<Job> jobslist;
    private Integer status;
    private Integer jobid;
    private String jobname;
    private String jobCode;

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public List<Job> getJobslist() {

        return jobslist;
    }

    public Integer getStatus() {
        return status;
    }

    public Integer getJobid() {
        return jobid;
    }

    public String getJobname() {
        return jobname;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setJobid(Integer jobid) {
        this.jobid = jobid;
    }

    public void setJobname(String jobname) {
        this.jobname = jobname;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public void filter() {
        // 10/5/2017
        // new code:
        jobslist = jobservice.findJob(jobid, jobname, status, jobCode);

        // old code:
        //jobslist=jobservice.findJob(jobid,jobname,status); 
    }

}
