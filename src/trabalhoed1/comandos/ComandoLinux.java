package trabalhoed1.comandos;

import trabalhoed1.exceptions.ComandoInvalidoException;
import trabalhoed1.funcoes.Funcao;

public enum ComandoLinux implements Comando{
    MKDIR("mkdir", new ComandoMkdir()),
    TOUCH("touch", new ComandoTouch()),
    LIST("ls", new ComandoLs()),
    SEARCH("search", new ComandoSearch());
    
    private String opcao;
    private Funcao funcao;

    private ComandoLinux(String opcao, Funcao funcao) {
        this.opcao = opcao;
        this.funcao = funcao;
    }

    @Override
    public String getOpcao() {
        return opcao;
    }

    @Override
    public Funcao getFuncao() {
        return funcao;
    }
    
    public static ComandoLinux opcaoPelaString(String op) throws ComandoInvalidoException{
        for(ComandoLinux comando : ComandoLinux.values()){
            if(comando.opcao.equals(op)){
                return comando;
            }
        }
        //Add exception certinha
        throw new ComandoInvalidoException(op);
    }
}
