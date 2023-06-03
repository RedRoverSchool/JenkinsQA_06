package school.redrover.model.base;

public class BaseComponent<Page extends BasePage<?>> extends BaseModel {

    private final Page page;

    public BaseComponent(Page page) {
        super(page.getDriver());
        this.page = page;
    }

    protected Page getPage() {
        return page;
    }
}
