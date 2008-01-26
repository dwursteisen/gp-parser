/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.utils;

/**
 *
 * @author Dav
 */
public interface MusicSVGLogger {
    public void error(Class clazz, String msg);
    public void error(Class clazz, String msg, Throwable ex);
    public void warm (Class clazz, String msg);
    public void warm (Class clazz, String msg, Throwable ex);
    public void info (Class clazz, String msg);
    public void info (Class clazz, String msg, Throwable ex);
    public void debug(Class clazz, String msg);
    public void debug(Class clazz, String msg, Throwable ex);
}
