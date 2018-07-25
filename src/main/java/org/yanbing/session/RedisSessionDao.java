package org.yanbing.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.SerializationUtils;
import org.yanbing.util.JedisUtil;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class RedisSessionDao extends AbstractSessionDAO {

    @Autowired
    private JedisUtil jedisUtil;

    private static final String SHIRO_SESSION_PREFIX="shiro_session";

    private byte[] getKey(String key){
        return (SHIRO_SESSION_PREFIX+key).getBytes();
    }

    private boolean isSessionNotNull(Session session){
        return session!=null && session.getId()!=null;
    }

    private void saveSession(Session session){
        if (isSessionNotNull(session)) {
            byte[] key = getKey(session.getId().toString());
            byte[] value = SerializationUtils.serialize(session);
            jedisUtil.set(key, value, 600);
        }
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId=generateSessionId(session);
        assignSessionId(session,sessionId);
        saveSession(session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        System.out.println("read Session "+sessionId);
        if (sessionId != null) {
            byte[] key = getKey(sessionId.toString());
            byte[] value=jedisUtil.get(key);
            return (Session) SerializationUtils.deserialize(value);
        }
        return null;
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        saveSession(session);
    }

    @Override
    public void delete(Session session) {
        if (isSessionNotNull(session)){
            byte[] key=getKey(session.getId().toString());
            jedisUtil.del(key);
        }
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Set<byte[]> keys=jedisUtil.keys(SHIRO_SESSION_PREFIX);
        return keys.stream().map(key->
            (Session) SerializationUtils.deserialize(jedisUtil.get(key))
        ).collect(Collectors.toSet());
    }
}
