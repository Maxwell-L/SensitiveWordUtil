package cn.maxwell.sensitive.word;

import cn.maxwell.exception.NullOrEmptyBranchException;

import java.util.List;

/**
 * @author linliangqi
 * @date 2021/09/03
 */
public interface SensitiveWordFilterService {

    public void addSensitiveWord(String word) throws NullOrEmptyBranchException;

    public void addAllSensitiveWord(List<String> words) throws NullOrEmptyBranchException;

    public List<String> getSensitiveWordList();

    public boolean isContainsSensitiveWord(String text);

    public List<String> filterTextWithSensitiveWords(List<String> originalTexts);

    public String replaceSensitiveWordsWithAsterisk(String text);
}
