package ProjectPOO;

class RepositoryTransaction {
    public static void searchTransactionUuid(String uuid){
        for (int i = 0; i < ControllerTransaction.allTransactions.size(); i++) {
            if (ControllerTransaction.allTransactions.get(i).id.equals(uuid)){
                System.out.println(">TRANSACCION ENCONTRADA" + ControllerTransaction.allTransactions.get(i).type);
            }
        }
    }
}