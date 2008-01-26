/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.utils.impl;

import net.sourceforge.musicsvg.utils.MusicSVGLogger;
import org.apache.log4j.Logger;
/**
 *
 * @author Dav
 */
public class MusicSVGLoggerLog4jImpl implements MusicSVGLogger {

    public MusicSVGLoggerLog4jImpl() {
        
    }
    
    @Override
    public void error(Class clazz, String msg) {
        Logger.getLogger(clazz).error(msg);
    }

    @Override
    public void error(Class clazz, String msg, Throwable ex) {
        Logger.getLogger(clazz).error(msg, ex);
    }

    @Override
    public void warm(Class clazz, String msg) {
        Logger.getLogger(clazz).warn(msg);
    }

    @Override
    public void warm(Class clazz, String msg, Throwable ex) {
        Logger.getLogger(clazz).warn(msg, ex);
    }

    @Override
    public void info(Class clazz, String msg) {
        Logger.getLogger(clazz).info(msg);
    }

    @Override
    public void info(Class clazz, String msg, Throwable ex) {
        Logger.getLogger(clazz).info(msg, ex);
    }

    @Override
    public void debug(Class clazz, String msg) {
        Logger.getLogger(clazz).debug(msg);
    }

    @Override
    public void debug(Class clazz, String msg, Throwable ex) {
        Logger.getLogger(clazz).debug(msg, ex);
    }

}
