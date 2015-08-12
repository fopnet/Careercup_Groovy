/**
 * Created by Felipe on 06/05/2015.
 */

def records =[[pq:'EnsurSet', funcao:'Sequestrador de H2S', uso:'Cimento'],
              [pq:'EnsurSet', funcao:'Sequestrador de H2S', uso:'Colchão'],
              [pq:'EnsurSet', funcao:'Sequestrador de H2S', uso:'FBA'],
              [pq:'EnsurSet', funcao:'Sequestrador de H2S', uso:'FBNA'],
              [pq:'EnsurSet', funcao:'Sequestrante de enxofre', uso:'Cimento'],
              [pq:'EnsurSet', funcao:'Sequestrante de enxofre', uso:'Colchão'],
              [pq:'EnsurSet', funcao:'Sequestrante de enxofre', uso:'FBA'],
              [pq:'EnsurSet', funcao:'Sequestrante de enxofre', uso:'FBNA'],
              [pq:'EnsurSet', funcao:'Sequestrante de H2S (gás sulfídrico)', uso:'Cimento'],
              [pq:'EnsurSet', funcao:'Sequestrante de H2S (gás sulfídrico)', uso:'Colchão'],
              [pq:'EnsurSet', funcao:'Sequestrante de H2S (gás sulfídrico)', uso:'FBA'],
              [pq:'EnsurSet', funcao:'Sequestrante de H2S (gás sulfídrico)', uso:'FBNA'],
              [pq:'EnsurSet2', funcao:'Funcao1', uso:null],
              [pq:'EnsurSet3', funcao:null, uso:'Uso1'],
]

def parserUsosProdutoQuimico(List records, def record) {
    Set usos = []
    int step = 1

    // se existir uso o step é a quantidade de usos
    if (records[record.row].uso) {
        while (record.row < records.size() && records[record.row].pq == record.pq) {
            if (!usos.add(records[record.row].uso))
                break;
            else
                record.row++
        }
        record.uso = usos.join(", ")
        record.row--
        step = usos.size()
    }
    record = parserFuncoesProdutoQuimico(records, step, record)
}

def parserFuncoesProdutoQuimico(List records, int step, def record) {
    def funcoes = []
    if ( records[record.row].funcao ) {
        // condição de parada é se a linha ainda é menor que os itens dos registros
        // e se o produto químico ainda é o mesmo
        while (record.row < records.size() && records[record.row].pq == record.pq) {
            funcoes << records[record.row].funcao
            record.row+=step
        }
        record.funcao = funcoes.join(", ")
        record.row -= step
    }
    println record
    record
}


def record  = [pq:records[0].pq, funcao: '', uso:'', row:0]

while ( (result = parserUsosProdutoQuimico(records, record)).row +1 < records.size() ) {
    record.pq = records[++result.row].pq
    record.row = result.row
    record.funcao =''
    record.uso =''
}

