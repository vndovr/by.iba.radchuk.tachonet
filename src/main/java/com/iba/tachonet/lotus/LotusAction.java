package com.iba.tachonet.lotus;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import lotus.domino.Database;
import lotus.domino.DateTime;
import lotus.domino.Document;
import lotus.domino.NotesException;
import lotus.domino.NotesFactory;
import lotus.domino.NotesThread;
import lotus.domino.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Vladimir Radchuk
 * 
 */
public abstract class LotusAction<T> {
    protected static final Log log = LogFactory.getLog(LotusAction.class);

    private static final String ENV_PATH = "TCN_PATH";
    private static final String CARDS_DB = "ETCards.nsf";
    private static final String REQUESTS_DB = "ETApplications.nsf";
    private static final String LOGS_DB = "ETJobs.nsf";

    protected static final String SEPARATOR = "@";

    protected Session session;
    protected String dbPath;

    private Database dbCards;
    private Database dbRequests;
    private Database dbLog;

    /**
     * Default constructor
     */
    public LotusAction() {
        super();
    }

    /**
     * Opens the connection to lotus notes databases
     * 
     * @throws Exception
     */
    public void connect() throws Exception {
        NotesThread.sinitThread();
        session = NotesFactory.createSessionWithFullAccess();
        dbPath = session.getEnvironmentString(ENV_PATH);
    }

    /**
     * Main action execution method
     * 
     * @return
     * @throws Exception
     */
    public abstract T execute() throws Exception;

    /**
     * Closes all connections
     * 
     * @throws Exception
     */
    public void disconnect() throws Exception {
        if (session != null)
            session.recycle();
        NotesThread.stermThread();
    }

    /**
     * @return
     * @throws NotesException
     */
    protected Database getCardsDb() throws NotesException {
        return dbCards == null ? dbCards = session.getDatabase(null, dbPath
                + "\\" + CARDS_DB, false) : dbCards;
    }

    /**
     * @return
     * @throws NotesException
     */
    protected Database getRequestsDb() throws NotesException {
        return dbRequests == null ? dbRequests = session.getDatabase(null,
                dbPath + "\\" + REQUESTS_DB, false) : dbRequests;
    }

    /**
     * @return
     * @throws NotesException
     */
    protected Database getLogDb() throws NotesException {
        return dbLog == null ? dbLog = session.getDatabase(null, dbPath + "\\"
                + LOGS_DB, false) : dbLog;
    }

    /**
     * @param values
     * @return
     */
    protected String buildKey(String... values) {
        StringBuilder sb = new StringBuilder();
        for (String value : values) {
            sb.append(SEPARATOR);
            sb.append(value);
        }
        return sb.substring(1);
    }

    /**
     * Returns the GregorianCalendar from the Lotus field
     * 
     * @param document
     * @param fieldName
     * @return
     * @throws NotesException
     */
    protected GregorianCalendar getGregorianCalendar(Document document,
            String fieldName) throws NotesException {
        GregorianCalendar gc = (GregorianCalendar) GregorianCalendar
                .getInstance();
        DateTime dt = (DateTime) document.getItemValueDateTimeArray(fieldName)
                .get(0);
        gc.setTime(dt.toJavaDate());
        return gc;
    }

    /**
     * Returns the date in correct format
     * 
     * @param document
     * @param fieldName
     * @return
     * @throws NotesException
     */
    protected String getFormattedDate(Document document, String fieldName)
            throws NotesException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        DateTime dt = (DateTime) document.getItemValueDateTimeArray(fieldName)
                .get(0);
        return sdf.format(dt.toJavaDate());
    }

}
