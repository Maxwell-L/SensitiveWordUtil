package cn.maxwell.trie;

import cn.maxwell.exception.NullOrEmptyBranchException;
import cn.maxwell.util.StringUtils;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * @author linliangqi
 * @Date 2021/08/29
 */
@Data
public class TrieTree {

    private TrieNode root;
    private int size;

    public TrieTree() {
        this.root = new TrieNode();
    }

    public void addAllBranchInTrieTree(List<String> branches) throws NullOrEmptyBranchException {
        if (branches.isEmpty()) {
            throw new NullOrEmptyBranchException("分支数量不能为0!");
        }
        for (String branch : branches) {
            addBranchInTrieTree(branch);
        }
        constructFailPointer();
    }

    public void addBranchInTrieTree(String branch) throws NullOrEmptyBranchException {
        if (StringUtils.isBlank(branch)) {
            throw new NullOrEmptyBranchException("新增分支不能为空!");
        }
        TrieNode node = root;
        char[] wordsInBranch = branch.toCharArray();
        for (char word : wordsInBranch) {
            if (!node.getChildren().containsKey(word)) {
                node.getChildren().put(word, new TrieNode());
            }
            node = node.getChildren().get(word);
        }
        if (!node.isEnd()) {
            node.setEnd(true);
            size++;
        }
        constructFailPointer();
    }

    public boolean isContainsBranch(String branch) {
        if (StringUtils.isBlank(branch)) {
            return false;
        }
        TrieNode node = root;
        char[] wordsInBranch = branch.toCharArray();
        for (char word : wordsInBranch) {
            while (!node.getChildren().containsKey(word)
                    && node != root) {
                node = node.getFail();
            }
            node = node.getChildren().get(word);
            if (node == null) {
                node = root;
            }
            if (node.isEnd()) {
                return true;
            }
        }
        return false;
    }

    private void constructFailPointer() {
        Queue<TrieNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TrieNode node = queue.remove();
            for (Map.Entry<Character, TrieNode> entry : node.getChildren().entrySet()) {
                TrieNode childrenNode = entry.getValue();
                if (node == root) {
                    childrenNode.setFail(root);
                } else {
                    TrieNode fail = node.getFail();
                    while (fail != null) {
                        TrieNode failChildrenNode = fail.getChildren().get(entry.getKey());
                        if (failChildrenNode != null) {
                            childrenNode.setFail(failChildrenNode);
                            break;
                        }
                        fail = fail.getFail();
                    }
                    if (fail == null) {
                        childrenNode.setFail(root);
                    }
                }
                queue.add(childrenNode);
            }
        }
    }

    public int size() {
        return size;
    }
}
