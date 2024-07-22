package wellsbabo.common.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
public class ApiPageRequest {

    private int limit;
    private int page;
    private int skipRows;

    public ApiPageRequest(int limit, int page) {
        this.limit = limit;
        this.page = page;
        this.skipRows = makeSkipRows();
    }

    public void setLimit(int limit) {
        this.limit = limit;
        this.skipRows = makeSkipRows();
    }

    public void setPage(int page) {
        this.page = page;
        this.skipRows = makeSkipRows();
    }

    private int makeSkipRows() {
        return (this.page - 1) * this.limit;
    }

}
