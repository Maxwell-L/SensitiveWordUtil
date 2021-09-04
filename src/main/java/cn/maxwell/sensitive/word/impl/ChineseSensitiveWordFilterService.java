package cn.maxwell.sensitive.word.impl;

import cn.maxwell.exception.NullOrEmptyBranchException;
import cn.maxwell.sensitive.word.SensitiveWordFilterService;
import cn.maxwell.trie.TrieTree;
import cn.maxwell.util.CollectionUtils;
import cn.maxwell.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author linliangqi
 * @date 2021/09/03
 */
public class ChineseSensitiveWordFilterService implements SensitiveWordFilterService {

    private volatile static ChineseSensitiveWordFilterService chineseSensitiveWordFilterService;

    private List<String> sensitiveWordList;

    private TrieTree trieTree;

    private ChineseSensitiveWordFilterService(){}

    public static ChineseSensitiveWordFilterService getInstance() {
        if (chineseSensitiveWordFilterService == null) {
            synchronized (ChineseSensitiveWordFilterService.class) {
                if (chineseSensitiveWordFilterService == null) {
                    chineseSensitiveWordFilterService = new ChineseSensitiveWordFilterService();
                }
            }
        }
        return chineseSensitiveWordFilterService;
    }



    @Override
    public void addSensitiveWord(String word) throws NullOrEmptyBranchException {
        initTrieTree();
        trieTree.addBranchInTrieTree(word);
        addSensitiveWordToList(word);
    }

    @Override
    public void addAllSensitiveWord(List<String> words) throws NullOrEmptyBranchException {
        initTrieTree();
        trieTree.addAllBranchInTrieTree(words);
        addAllSensitiveWordToList(words);
    }

    @Override
    public List<String> getSensitiveWordList() {
        initSensitiveWordList();
        return this.sensitiveWordList;
    }

    @Override
    public boolean isContainsSensitiveWord(String text) {
        initTrieTree();
        if (CollectionUtils.isEmpty(this.sensitiveWordList)
                || StringUtils.isBlank(text)) {
            return false;
        }
        return this.trieTree.isContainsBranch(text);
    }

    @Override
    public List<String> filterTextWithSensitiveWords(List<String> originalTexts) {
        if (CollectionUtils.isEmpty(originalTexts)) {
            return new ArrayList<>();
        }
        initTrieTree();
        List<String> result = new ArrayList<>();
        originalTexts.stream().forEach(text -> {
            if (!StringUtils.isBlank(text)
                    && !isContainsSensitiveWord(text)) {
                result.add(text);
            }
        });
        return result;
    }

    @Override
    public String replaceSensitiveWordsWithAsterisk(String text) {
        return null;
    }

    private void initTrieTree() {
        if (this.trieTree == null) {
            this.trieTree = new TrieTree();
        }
    }

    private void addSensitiveWordToList(String word) {
        initSensitiveWordList();
        this.sensitiveWordList.add(word);
    }

    private void addAllSensitiveWordToList(List<String> words) {
        initSensitiveWordList();
        words.stream().forEach(word -> this.sensitiveWordList.add(word));
    }

    private void initSensitiveWordList() {
        if (CollectionUtils.isEmpty(this.sensitiveWordList)) {
            this.sensitiveWordList = new ArrayList<>();
        }
    }
}
