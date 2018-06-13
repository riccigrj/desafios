package idwall.desafio.crawler.telegram;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;
import idwall.desafio.crawler.Crawler;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ricci
 */
public class Bot {

    private TelegramBot bot = new TelegramBot("564641270:AAEJ3X5T4GQt0iKwvbTKydQrSt6dNnjcBAo");
    private int contMensagem = 0;

    public void inicializar() {

    }

    public List<Update> getMensagem() {
        GetUpdatesResponse mensagem = bot.execute(new GetUpdates().limit(100).offset(contMensagem).timeout(20000));
        List<Update> updates = mensagem.updates();
        return updates;
    }

    public List<String> getResposta(Update update) {
        List<String> resposta = new ArrayList();

        if (update.message() != null) {
            String mensagem = update.message().text();
            if (mensagem.startsWith("/start")) {
                resposta.add("Seja bem-vindo ao crawler Reddit por Gabriel Ricci\n"
                        + "Insira abaixo os subreddits que deseja visualizar uma lista das threads mais bombadas.\n"
                        + "Usando o comando /NadaPraFazer e separando os subreddits por ';'\n"
                        + "Ex.. /NadaPraFazer programming;dogs;brazil\n");

            } else if (mensagem.startsWith("/NadaPraFazer")) {
                enviarResposta(update.message().chat().id(), "Ok! Aguarde um momento enquanto estamos coletando as informações.");
                String[] cmdMensagem = mensagem.split("/NadaPraFazer ");
                Crawler crawler = new Crawler();
                Map<String, List<idwall.desafio.model.Thread>> subThreads = crawler.getThreadsMaisPopulares(cmdMensagem[1]);
                for (String subreddit : subThreads.keySet()) {
                    List<idwall.desafio.model.Thread> threads = subThreads.get(subreddit);
                    if (!threads.isEmpty()) {
                        resposta.add("THREAD " + subreddit.toUpperCase());
                        for (idwall.desafio.model.Thread thread : threads) {
                            resposta.add(thread.toString());
                        }
                    } else {
                        resposta.add("Não existem threads bombando (acima de 5000) no subreddit " + subreddit);
                    }
                }

            } else {
                resposta.add("Insira abaixo os subreddits que deseja visualizar uma lista das threads mais bombadas.\n"
                        + "Usando o comando /NadaPraFazer e separando os subreddits por ';'\n"
                        + "Ex.. /NadaPraFazer programming;dogs;brazil\n");
            }
        }
        return resposta;
    }

    public void incrementaContMenssagem(Update update) {
        contMensagem = update.updateId() + 1;
    }

    public boolean enviarResposta(Long idChat, String resposta) {
        SendResponse envio = bot.execute(new SendMessage(idChat, resposta));
        System.out.println(envio.description());
        return envio.isOk();
    }
}
