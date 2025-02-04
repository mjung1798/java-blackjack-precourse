package view;

import domain.card.Card;
import domain.result.GamerResult;
import domain.result.ResultGamersScore;
import domain.user.ActiveGamers;
import domain.user.Gamer;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    final public static String DELIMITER = ", ";

    public static void showInputPlayerNamesInfo() {
        System.out.println("게임에 참여한 사람의 이름을 입력하세요 (쉼표 기준으로 분리)");
    }

    public static void showInputPlyerBettingMoneyInfo(String playerName) {
        System.out.printf("%s의 배팅 금액은?\n", playerName);
    }

    public static void showGiveCardToEachInfo(List<String> playerName) {
        String nameCollect = String.join(DELIMITER, playerName);
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.\n", nameCollect);
    }

    public static void showGamersCard(ActiveGamers activeGamers) {
        for (Gamer gamer : activeGamers.getGamers()) {
            System.out.println(makePrintfFormatOfGamer(gamer));
        }
    }

    public static void showOneGamerCard(Gamer gamer) {
        System.out.println(makePrintfFormatOfGamer(gamer));
    }

    public static void showGamersCardWithScore(ActiveGamers activeGamers) {
        for (Gamer gamer : activeGamers.getGamers()) {
            System.out.printf("%s - 결과 : %d\n", makePrintfFormatOfGamer(gamer), gamer.getScoreOfGamer());
        }
    }

    public static String makePrintfFormatOfGamer(Gamer gamer) {
        String gamerName = gamer.getName();

        String cardInfo = gamer.getCards().stream()
                .map(OutputView::showOneCard)
                .collect(Collectors.joining(", "));

        return gamerName + " : " + cardInfo;
    }

    private static String showOneCard(Card card) {
        return card.getSymbol().getName() + card.getType().getName();
    }

    public static void showGetOneMoreCardInfo(Gamer gamer) {
        String gamerName = gamer.getName();
        System.out.printf("%s는 한장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)", gamerName);
    }

    public static void showGetMoreCardForDealer() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void showResult(ResultGamersScore resultGamersScores) {
        System.out.println("## 최종 수익");
        for (GamerResult gamerResult : resultGamersScores.getGamerResults()) {
            showGamersResult(gamerResult);
        }

    }

    public static void showGamersResult(GamerResult gameResult) {
        System.out.printf("%s : %.0f\n", gameResult.getName(), gameResult.getResultMoney());
    }
}
