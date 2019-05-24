package i18n;

import java.util.ListResourceBundle;

public class MyClassBundle_zh extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return contents;
    }
    private Object[][] contents = {
            { "price"   , new Double(13.00) },
            { "currency", "RMB" },
    };
}
