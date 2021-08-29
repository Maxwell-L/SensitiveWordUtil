package cn.maxwell.trie;

import lombok.Data;

import java.util.HashMap;

/**
 * @author linliangqi
 * @date 2021/08/29
 */
@Data
public final class TrieNode {
    private Boolean isEnd;
    private TrieNode fail;
    private HashMap<Character, TrieNode> children;

    public TrieNode(Boolean isEnd) {
        this.isEnd = isEnd;
        this.fail = null;
        this.children = new HashMap<>();
    }

    public TrieNode() {
        this(false);
    }
}
