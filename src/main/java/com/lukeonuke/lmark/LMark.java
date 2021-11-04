/*
 * MIT License
 *
 * Copyright (c) 2021 Luka Kresoja
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 * */
package com.lukeonuke.lmark;


import com.lukeonuke.lmark.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.FileNotFoundException;

/**
 * LMARK
 * ======
 * Simple and effective markdown editor.
 */
public class LMark {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(LMark.class);
        System.out.println("\n _                          _    \n" +
                    "| |                        | |   \n" +
                    "| |    _ __ ___   __ _ _ __| | __\n" +
                    "| |   | '_ ` _ \\ / _` | '__| |/ /\n" +
                    "| |___| | | | | | (_| | |  |   < \n" +
                    "\\_____/_| |_| |_|\\__,_|_|  |_|\\_\\\n" +
                    "                                 \n" +
                    "Lmark - simple and os integrated markdown editor");
        //Load all mandatory subsystems
        registerToDesktop();
        logger.info("Registered to desktop");
        //Load registry
        Registry.getInstance();
        logger.info("Loaded registry");
        //Finished base boot.
        logger.info("Finished loading all subsystems, starting gui");
        LMarkApplication.launchApp(args);
    }

    private static void registerToDesktop(){
        if(Desktop.isDesktopSupported()){
            Desktop desktop = Desktop.getDesktop();
            if(desktop.isSupported(Desktop.Action.APP_OPEN_FILE)){
                desktop.setOpenFileHandler(e -> {
                    try {
                        FileUtils.getInstance(e.getFiles().get(0).getPath());
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                        System.exit(-1);
                    }
                });
            }
        }
    }
}