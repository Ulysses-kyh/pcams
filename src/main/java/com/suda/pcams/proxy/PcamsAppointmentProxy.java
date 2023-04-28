package com.suda.pcams.proxy;

import com.suda.pcams.model.PcamsAppointment;
import com.suda.pcams.model.PcamsSetting;
import com.sun.xml.internal.org.jvnet.mimepull.MIMEMessage;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import xyz.erupt.annotation.fun.DataProxy;
import xyz.erupt.core.context.MetaContext;
import xyz.erupt.job.model.EruptMail;
import xyz.erupt.jpa.dao.EruptDao;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import javax.persistence.Transient;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Optional;

/**
 * 实现预约状态更新的业务逻辑
 * 预约成功后需发送邮件通知相关心理老师
 *
 * @author Ulysses
 * @since 2023-04-28
 */
@Service
public class PcamsAppointmentProxy implements DataProxy<PcamsAppointment> {
    PcamsSetting pcamsSetting;
    @Resource
    private EruptDao eruptDao;
    @Resource
    private JavaMailSenderImpl javaMailSender;

    @Override
    public void beforeAdd(PcamsAppointment pcamsAppointment) {
        // 新增预约前，获取setting表
        Long tempId = pcamsAppointment.getPcamsSetting().getId();
        pcamsSetting = eruptDao.getEntityManager().find(PcamsSetting.class, tempId);
    }

    @SneakyThrows
    public void afterAdd(PcamsAppointment pcamsAppointment) {
        // 新增预约后，修改预约时间管理为已预约
        pcamsSetting.setStatus(1);
        eruptDao.merge(pcamsSetting);

        // 获取邮件相关信息
        String date = pcamsSetting.getDate();
        String content = "<p>你在" + date + "的心理咨询时间安排已被预约</p>";
        String recipient = pcamsSetting.getPcamsTeacher().getTeacherMail();
        String subject = date + "的心理咨询时间安排已被预约";

        // 发送邮件
        EruptMail eruptMail = new EruptMail();
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());
        helper.setFrom("ulyssesk@foxmail.com");
        helper.setTo(recipient);
        helper.setSubject(subject);
        helper.setText(content,true);

        try {
            javaMailSender.send(mimeMessage);
            eruptMail.setStatus(true);
        } catch (Exception var5){
            var5.printStackTrace();
            eruptMail.setStatus(false);
            Optional.ofNullable(var5.toString()).ifPresent((it) -> {
                eruptMail.setErrorInfo(it.substring(0, 5000));
            });
        }

        // 邮件入库
        eruptMail.setCreateBy("预约自动通知");
        eruptMail.setCreateTime(new Date());
        eruptMail.setContent(content);
        eruptMail.setRecipient(recipient);
        eruptMail.setSubject(subject);
        eruptDao.persist(eruptMail);
    }

}
