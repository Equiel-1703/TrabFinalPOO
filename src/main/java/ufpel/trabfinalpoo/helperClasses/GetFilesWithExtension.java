package ufpel.trabfinalpoo.helperClasses;

import java.io.File;
import java.io.FilenameFilter;

public class GetFilesWithExtension {

    public static String[] getAllFilesWithCertainExtension(File folder, String filterExt)
    {
        MyExtFilter extFilter = new MyExtFilter(filterExt);
        if(!folder.isDirectory())
        {
            System.err.println("Insira uma pasta para realizar a busca por arquivos!");
            System.exit(1);
        }

        // list out all the file name and filter by the extension
        String[] list = folder.list(extFilter);

        return list;
    }

    // inner class, generic extension filter
    private static class MyExtFilter implements FilenameFilter {

        private String ext;

        public MyExtFilter(String ext) {
            this.ext = ext;
        }

        public boolean accept(File dir, String name) {
            return (name.endsWith(ext));
        }
    }
}
