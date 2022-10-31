package edai.practices.tema3.filefinder;

import edai.collections.Stack;

public abstract class Entry {
    private String name;
    private Folder parent;

    Entry(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Folder getParent() {
        return parent;
    }

    public void setParent(Folder value) {
        parent = value;
    }

    public String getPath() {

        Stack<String> pathParts = new Stack<String>();
        Entry current = this;
        while (current != null) {
            pathParts.push(current.getName());
            current = current.getParent();
        }

        StringBuilder builder = new StringBuilder();

        builder.append(pathParts.pop());
        while (!pathParts.isEmpty()) {
            builder.append('/').append(pathParts.pop());
        }

        return builder.toString();
    }
}
