package cn.maxwell.exception;

/**
 * @author linliangqi
 * @date 2021/08/29
 */
public class NullOrEmptyBranchException extends Exception {
    public NullOrEmptyBranchException() {
        super();
    }

    public NullOrEmptyBranchException(String message) {
        super(message);
    }

}
