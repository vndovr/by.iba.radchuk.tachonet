package com.iba.tachonet.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author Vladimir Radchuk
 * 
 */
public class Timer {
    private List<Split> splits = new ArrayList<Split>();
    private String name = null;

    /**
     * Timer constructor comment.
     */
    public Timer(String name) {
        super();
        this.name = name;
    }

    /**
     * Adds the Split with name to the Timer
     * 
     * @param s
     */
    public void mark(String s) {
        splits.add(new Split(s));
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("Timer(").append(name).append(") {\n");
        for (Iterator<Split> i = splits.iterator(); i.hasNext();) {
            buf.append(" ").append(i.next().toString()).append("\n");
        }
        buf.append("}");
        return buf.toString();
    }

    /**
     * @author Vladimir Radchuk
     * 
     */
    public class Split {
        private final String text;
        private final Date time;

        /**
         * @param text
         */
        public Split(String text) {
            this.text = text;
            this.time = new Date();
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            String s = "Split { ";
            if (text != null)
                s += text + ", ";
            s += new SimpleDateFormat("HH:mm:ss.SSSS").format(time) + " }";
            return s;
        }
    }

}
