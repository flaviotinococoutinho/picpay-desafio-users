package com.picpay.testebackend.util;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.picpay.testebackend.mapper.UserMapper;
import com.picpay.testebackend.model.User;
import com.picpay.testebackend.repository.es.IUserESRepository;
import com.picpay.testebackend.repository.rdbms.IUserRdbmsRepository;

@Service
public class ElasticSynchronizer {

    private IUserRdbmsRepository userDAO;
    private IUserESRepository userESRepo;
    private UserMapper userMapper;

    private static final Logger LOG = LoggerFactory.getLogger(ElasticSynchronizer.class);

    @Autowired
    public ElasticSynchronizer(IUserRdbmsRepository userDAO, IUserESRepository userESRepo, UserMapper userMapper) {
        this.userDAO = userDAO;
        this.userESRepo = userESRepo;
        this.userMapper = userMapper;
    }

    @Scheduled(cron = "0 */3 * * * *")
    @Transactional
    public void sync() {
        LOG.info("Start Syncing - {}", LocalDateTime.now());
        this.syncUsers();
        LOG.info(" End Syncing - {}", LocalDateTime.now());
    }

    //TODO Esse sincronismo deve ser feito pelo logdash
    private void syncUsers() {

        Specification<User> userSpecification = (root, criteriaQuery, criteriaBuilder) ->
                getModificationDatePredicate(criteriaBuilder, root);
        List<User> userList;
        if (userESRepo.count() == 0) {
        	// Limitado para evitar estouro de memória
            userList = userDAO.allLimited(20000);
        } else {
            userList = userDAO.findAll(userSpecification);
        }
        for(User user: userList) {
            LOG.info("Syncing User - {}", user.getId());
            userESRepo.save(this.userMapper.toUserModel(user));
        }
    }

    private static Predicate getModificationDatePredicate(CriteriaBuilder cb, Root<?> root) {
        Expression<Timestamp> currentTime;
        currentTime = cb.currentTimestamp();
        Expression<Timestamp> currentTimeMinus = cb.literal(new Timestamp(System.currentTimeMillis() -
                (Constants.INTERVAL_IN_MILLISECONDE)));
        return cb.between(root.<Date>get(Constants.MODIFICATION_DATE),
                currentTimeMinus,
                currentTime
        );
    }
}
