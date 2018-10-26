import domain.*;
import view.InputView;
import view.ResultView;

import java.io.IOException;

public class LottoGame {
    public static void main(String[] args) {
        try {
            goLotto();
        } catch (IllegalArgumentException|IOException e){
            System.out.println(e.getMessage());
            main(args);
        }
    }

    private static void goLotto() throws IOException {
        LottoBundle theLottoBundle = makeLottoBundle();
        ResultView.showLottoBundles(theLottoBundle.toDto());

        WinStats theWinStats = makeWinStats(theLottoBundle);
        ResultView.showResultMessage(theWinStats.toDto());
    }

    private static LottoBundle makeLottoBundle() throws IOException {
        int totalLottoCount = InputView.inputCount();
        int manualLottoCount = InputView.inputManualLottoCount();
        if(manualLottoCount > totalLottoCount)
            throw new IllegalArgumentException("Can't enter a number greater than the total number.");
        LottoBundle theLottoBundle =
                LottoFactory.generateLottoBundle(totalLottoCount, InputView.inputManualLottoTexts(manualLottoCount));
        return theLottoBundle;
    }

    private static WinStats makeWinStats(LottoBundle theLottoBundle) throws IOException {
        WinningLotto theWinningLotto =
                LottoFactory.generateWinningLotto(InputView.inputWinningLottoNumbers(), InputView.inputBonusBall());
        WinStats theWinStats = theLottoBundle.calculateWinStats(theWinningLotto);
        return theWinStats;
    }
}
