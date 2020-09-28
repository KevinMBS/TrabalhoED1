package TrabalhoED1.path;

import TrabalhoED1.lista.ListaEncadeada;
import TrabalhoED1.elementos.Arquivo;
import TrabalhoED1.elementos.Diretorio;
import TrabalhoED1.exceptions.NaoEDiretorioException;
import TrabalhoED1.exceptions.PathInvalidoException;

public abstract class InterpretaPath {
    //Feito por Kevin e Rafael
    public static Arquivo interpreta(ListaEncadeada lista, String path) throws PathInvalidoException, NaoEDiretorioException{
        //Provavelmente a função mais complicada de se explicar no momento desse comentário
        String[] dirsAVisitar = path.split("/"); //Array de string com a divisão dos diretorios no path dado
        ListaEncadeada dirAtual = lista;
        Arquivo atual = dirAtual.getRaiz();
        int i, tamanho;
        
        i = 0;
        tamanho = dirsAVisitar.length-1; //-1 por causa do offset do i = 0
        while(atual != null){
            if(atual.getChave().equals(dirsAVisitar[i]) && i < tamanho){
                //Compara se a chave de atual é igual ao que está no array
                if(atual instanceof Diretorio){
                    //Se for diretorio mesmo, entra nessa lista e modifica o dirAtual e
                    //o arquivo atual além de passar para a prox posição do dirsAVisitar
                    dirAtual = ((Diretorio) atual).getDir();
                    atual = dirAtual.getRaiz();
                    i++;
                }else{
                    //Semi certeza que não é possivel chegar aqui, mas até eu ter certeza, isso permanece
                    throw new NaoEDiretorioException(path);
                }
            }else if(atual.getChave().equals(dirsAVisitar[i]) && i == tamanho){
                //Se chegarmos aqui, achamos o que queriamos achar
                if(atual instanceof Diretorio){
                    //...caso ele for um diretorio
                    return atual;
                }else{
                    //se ele for arquivo, lança uma exception
                    throw new NaoEDiretorioException(path);
                }
            }else{
                //Passa para o proximo elemento na lista
                atual = atual.getProx();
            }
        }
        
        //Não foi achado, retorna null
        return null;
        
        /*
        for(String dir:dirsAVisitar){
            try{
                atual = dirAtual.getRaiz();
            }catch(Exception e){
                //sei que deu merda mas não sei oq fazer
                throw new PathInvalidoException("");
            }
            Arquivo aux = new Arquivo(dir);
            while(true){
                if(atual == null){
                    //chegou ao fim da lista,logo diretorio não existe
                    throw new PathInvalidoException("");
                    
                    //chegou no fim da lista,ou seja, não encontrou o diretório ou arquivo procurado
                    //break;
                }
                if(atual.equals(aux)){
                    //encontrou
                    break;
                }
                atual = atual.getProx();
            }
            if(atual instanceof Diretorio)
                dirAtual = ((Diretorio)atual).getDir();
            
        }
        return atual;
        */
    }
}
