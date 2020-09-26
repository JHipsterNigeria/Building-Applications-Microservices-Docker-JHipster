import { IVirtualAccount } from 'app/shared/model/coreservice/virtual-account.model';
import { TransactionType } from 'app/shared/model/enumerations/transaction-type.model';

export interface ITransactionRequest {
  id?: number;
  amount?: number;
  channel?: string;
  currency?: string;
  sourceAccount?: string;
  sourceAccountBankCode?: string;
  sourceAccountName?: string;
  sourceNarration?: string;
  destinationAccount?: string;
  destinationAccountBankCode?: string;
  destinationAccountName?: string;
  destinationNarration?: string;
  statusWebHook?: string;
  transactionRef?: string;
  responseCode?: string;
  responseMessage?: string;
  transactionType?: TransactionType;
  schemeOwnerId?: string;
  accounts?: IVirtualAccount[];
}

export class TransactionRequest implements ITransactionRequest {
  constructor(
    public id?: number,
    public amount?: number,
    public channel?: string,
    public currency?: string,
    public sourceAccount?: string,
    public sourceAccountBankCode?: string,
    public sourceAccountName?: string,
    public sourceNarration?: string,
    public destinationAccount?: string,
    public destinationAccountBankCode?: string,
    public destinationAccountName?: string,
    public destinationNarration?: string,
    public statusWebHook?: string,
    public transactionRef?: string,
    public responseCode?: string,
    public responseMessage?: string,
    public transactionType?: TransactionType,
    public schemeOwnerId?: string,
    public accounts?: IVirtualAccount[]
  ) {}
}
