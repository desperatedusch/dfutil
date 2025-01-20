package de.dfutil.core.files.parsing;

import org.springframework.context.annotation.Profile;

import java.util.List;

@Profile({"procedural-persisting ", "!eventbased-persisting"})
public interface Processing<T> {

    static <T extends Processing<T>> T buildChain(List<T> elements, T lastElement) {
        if (elements.isEmpty()) {
            return lastElement;
        }
        for (int i = 0; i < elements.size(); i++) {
            var current = elements.get(i);
            var next = i < elements.size() - 1 ? elements.get(i + 1) : lastElement;
            current.setNext(next);
        }
        return elements.get(0);
    }

    void setNext(T next);

}
