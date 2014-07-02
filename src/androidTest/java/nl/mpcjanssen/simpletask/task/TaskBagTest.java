package nl.mpcjanssen.simpletask.task;

import android.app.Activity;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

import nl.mpcjanssen.simpletask.ActiveFilter;
import nl.mpcjanssen.simpletask.remote.FileStoreInterface;

/**
 * Created with IntelliJ IDEA.
 * User: Mark Janssen
 * Date: 21-7-13
 * Time: 12:28
 */

public class TaskBagTest extends TestCase {

    public void testInit() {
        ArrayList<String> lines = new ArrayList<String>();
        lines.add("Test");
        lines.add("Test2");
        TestFileStore testFileStore = new TestFileStore(lines);
        TaskCache tb = new TaskCache(null, testFileStore, null);
        assertEquals(2, tb.size());
        assertEquals("Test", tb.getTaskAt(0).inFileFormat());
        assertEquals(0, tb.getContexts(false).size());
    }

    public void testSimpleFilter() {
        ArrayList<String> lines = new ArrayList<String>();
        lines.add("Test");
        lines.add("Test2 @Match");
        TestFileStore testFileStore = new TestFileStore(lines);
        TaskCache tb = new TaskCache(null, testFileStore, null);
        ActiveFilter filter = new ActiveFilter();
        ArrayList<String> contexts = new ArrayList<String>();
        contexts.add("NoMatch");
        filter.setContexts(contexts);
        ArrayList<Task> visibleTasks = filter.apply(tb.getTasks());
        assertEquals(0, visibleTasks.size());
        contexts.clear();
        contexts.add("Match");
        filter.setContexts(contexts);
        visibleTasks = filter.apply(tb.getTasks());
        assertEquals(1, visibleTasks.size());
    }

    class TestFileStore implements FileStoreInterface {

        private ArrayList<String> mContents;

        public TestFileStore(ArrayList<String> contents) {
            mContents = contents;
        }

        @Override
        public boolean isAuthenticated() {
            return false;
        }

        @Override
        public ArrayList<String> get(String path) {
            return mContents;
        }

        @Override
        public void append(String path, List<String> lines) {

        }

        @Override
        public void startLogin(Activity caller, int i) {

        }

        @Override
        public void startWatching(String path) {

        }

        @Override
        public void stopWatching(String path) {

        }


        @Override
        public void deauthenticate() {

        }


        @Override
        public void browseForNewFile(Activity act, String path, FileSelectedListener listener) {

        }

        @Override
        public void update(String mTodoName, List<String> original, List<String> updated) {

        }

        @Override
        public void delete(String mTodoName, List<String> strings) {

        }

        @Override
        public int getType() {
            return 0;
        }

        @Override
        public void move(String sourcePath, String targetPath, ArrayList<String> strings) {

        }

    }
}
