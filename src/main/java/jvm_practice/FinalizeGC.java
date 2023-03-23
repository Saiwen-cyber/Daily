package jvm_practice;




/**
 * @author fang
 */
public class FinalizeGC {
    public static FinalizeGC SAVE_HOOK = null;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed！");
        FinalizeGC.SAVE_HOOK = this;
    }
    public void isAlive() {
        System.out.println("I am alive");
    }

    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new FinalizeGC();

        SAVE_HOOK = null;
        System.gc();
        //F-queue 执行，只是触发，不会等待他方法执行完成
        Thread.sleep(500);
        if(SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("dead");
        }

        //finalize 执行过 也会被第二次标记
        SAVE_HOOK = null;
        System.gc();
        Thread.sleep(500);
        if(SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("dead");
        }
    }
}
