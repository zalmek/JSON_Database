class Article implements Comparable<Article> {
    private String title;
    private int size;

    public Article(String title, int size) {
        this.title = title;
        this.size = size;
    }

    public String getTitle() {
        return this.title;
    }

    public int getSize() {
        return this.size;
    }

    @Override
    public int compareTo(Article otherArticle) {
        if (Integer.compare(size, otherArticle.size) != 0) {
            return Integer.compare(size, otherArticle.size);
        } else {
            return Integer.compare(title.compareTo(otherArticle.title), 0);
        }
    }
}