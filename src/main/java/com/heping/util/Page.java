package com.heping.util;

public class Page {

    private int showCount; //每页显示记录数
    private int totalPage;        //总页数
    private int totalResult;    //总记录数
    private int currentPage;    //当前页
    private int currentResult;    //当前记录起始索引
    private boolean entityOrField;    //true:需要分页的地方，传入的参数就是Page实体；false:需要分页的地方，传入的参数所代表的实体拥有Page属性
    private String pageStr;        //最终页面显示的底部翻页导航，详细见：getPageStr();
    private PageData pd = new PageData();
    public static final String DEFAULT_FUNCTION_NAME = "toPage";
    public static final String DEFAULT_UL_CLASS_NAME = "pagination-lg";

    public Page() {
        try {
            this.showCount = Const.PAGE;
        } catch (Exception e) {
            this.showCount = 10;
        }
    }

    public static Page getPage(int currentPage, PageData pd) {
        Page p = new Page();
        if (pd != null) {
            p.setPd(pd);
        }
        p.setCurrentPage(currentPage);
        return p;
    }

    public static Page getPage(int currentPage) {
        return getPage(currentPage, null);
    }

    public int getTotalPage() {
        if(totalResult > 0){
            return totalPage = (totalResult  +  showCount  - 1) / showCount;
        }
        return 1;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    public int getCurrentPage() {
        if (currentPage <= 0)
            currentPage = 1;
        if (currentPage > getTotalPage())
            currentPage = getTotalPage();
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setPageStr(String pageStr) {
        this.pageStr = pageStr;
    }

    public int getShowCount() {
        return showCount;
    }

    public void setShowCount(int showCount) {
        this.showCount = showCount;
    }

    public int getCurrentResult() {
        currentResult = (getCurrentPage() - 1) * getShowCount();
        if (currentResult < 0)
            currentResult = 0;
        return currentResult;
    }

    public void setCurrentResult(int currentResult) {
        this.currentResult = currentResult;
    }

    public boolean isEntityOrField() {
        return entityOrField;
    }

    public void setEntityOrField(boolean entityOrField) {
        this.entityOrField = entityOrField;
    }

    public PageData getPd() {
        return pd;
    }

    public void setPd(PageData pd) {
        this.pd = pd;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + currentPage;
        result = prime * result + currentResult;
        result = prime * result + (entityOrField ? 1231 : 1237);
        result = prime * result + ((pageStr == null) ? 0 : pageStr.hashCode());
        result = prime * result + ((pd == null) ? 0 : pd.hashCode());
        result = prime * result + showCount;
        result = prime * result + totalPage;
        result = prime * result + totalResult;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Page other = (Page) obj;
        if (currentPage != other.currentPage)
            return false;
        if (currentResult != other.currentResult)
            return false;
        if (entityOrField != other.entityOrField)
            return false;
        if (pageStr == null) {
            if (other.pageStr != null)
                return false;
        } else if (!pageStr.equals(other.pageStr))
            return false;
        if (pd == null) {
            if (other.pd != null)
                return false;
        } else if (!pd.equals(other.pd))
            return false;
        if (showCount != other.showCount)
            return false;
        if (totalPage != other.totalPage)
            return false;
        if (totalResult != other.totalResult)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Page [showCount=" + showCount + ", totalPage=" + totalPage + ", totalResult=" + totalResult
                + ", currentPage=" + currentPage + ", currentResult=" + currentResult + ", entityOrField="
                + entityOrField + ", pageStr=" + pageStr + ", pd=" + pd + "]";
    }

    public String getPageStr() {
        return this.pageStr;
    }

    public static void PageStr(Page p) {
        PageStr(p,DEFAULT_FUNCTION_NAME,DEFAULT_UL_CLASS_NAME);
    }



    public static void PageStr(Page p, String functionName,String ulClassName) {
        if(functionName == null){
            functionName = DEFAULT_FUNCTION_NAME;
        }
        if(ulClassName == null){
            ulClassName = DEFAULT_UL_CLASS_NAME;
        }
        int lastPage = p.getTotalPage();
        int currentPage = p.getCurrentPage();
        if (lastPage == 0) {
            lastPage = 1;
            //totalPage=1;
            currentPage = 1;
        }
        if(lastPage == 1){
            p.setPageStr("");
            return;
        }
        StringBuilder sb = new StringBuilder("<ul class='pagination "+ulClassName+"'>");
        String s1 = "";
        String s2 = "";
        if (currentPage == 1) {
            s1 = "disabled";//禁用上一页
        }
        if (currentPage == lastPage) {
            s2 = "disabled";//禁用下一页
        }
        if (s1.equals("disabled")) {
            sb.append("<li class='").append(s1).append("'><a>&laquo;</a></li>");
        } else {
            sb.append("<li class='").append(s1).append("'><a onclick=\"").append(functionName).append("(").append(currentPage - 1).append(")\" >&laquo;</a></li>");
        }
        if (currentPage - 1 >= 4) {//前面至少4页
            sb.append("<li><a onclick=\"").append(functionName).append("(1)\">1</a></li>");//第一页
            sb.append("<li class=\"" + "disabled" + "\"><span>...</span></li>");//省略号
            if (currentPage == lastPage) {//如果是最后一页
                sb.append("<li><a onclick=\"").append(functionName).append("(").append(currentPage - 3).append(")\" >").append(currentPage - 3).append("</a></li>");//前三页
            }
            sb.append("<li><a onclick=\"").append(functionName).append("(").append(currentPage - 2).append(")\" >").append(currentPage - 2).append("</a></li>");//前二页
            sb.append("<li><a onclick=\"").append(functionName).append("(").append(currentPage - 1).append(")\" >").append(currentPage - 1).append("</a></li>");//前一页
        } else {
            for (int i = 1; i < currentPage; i++) {
                sb.append("<li><a onclick=\"").append(functionName).append("(").append(i).append(")\" >").append(i).append("</a></li>");//前面不超过4页把前面的都显示出来
            }
        }
        sb.append("<li class=\"active\"><a onclick=\"").append(functionName).append("(").append(currentPage).append(")\" >").append(currentPage).append("</a></li>");//加上当前页码
        if (lastPage - currentPage >= 4) {//后面至少4页
            sb.append("<li><a onclick=\"").append(functionName).append("(").append(currentPage + 1).append(")\">").append(currentPage + 1).append("</a></li>");//后一页
            sb.append("<li><a onclick=\"").append(functionName).append("(").append(currentPage + 2).append(")\">").append(currentPage + 2).append("</a></li>");//后二页
            if (currentPage == 1) {//如果是第一页
                sb.append("<li><a onclick=\"").append(functionName).append("(").append(currentPage + 3).append(")\" >").append(currentPage + 3).append("</a></li>");//第三页
            }
            sb.append("<li class=\"" + "disabled" + "\"><span>...</span></li>");//省略号
            sb.append("<li><a onclick=\"").append(functionName).append("(").append(lastPage).append(")\" >").append(lastPage).append("</a></li>");//最后页
        } else {
            for (int i = currentPage + 1; i <= lastPage; i++) {
                sb.append("<li><a onclick=\"").append(functionName).append("(").append(i).append(")\" >").append(i).append("</a></li>");//后面不超过4页把后面的都显示出来
            }
        }
        if (s2.equals("disabled")) {
            sb.append("<li class='").append(s2).append("'><a>&raquo;</a></li>");
        } else {
            sb.append("<li class=\"").append(s2).append("\"><a onclick=\"").append(functionName).append("(").append(currentPage + 1).append(")\" >&raquo;</a></li>");
        }
        sb.append("</ul>");
        p.setPageStr(sb.toString());
    }

}
