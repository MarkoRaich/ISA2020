import { Dermatologist } from './dermatologist';
import { ExaminationType } from './examinationType';

export class AvailableExamination {

    id: number;
    startDateTime: string;
    endDateTime: string;
    examinationTypeDTO: ExaminationType;
    dermatologistDTO: Dermatologist;

    constructor(startDateTime: string, endDateTime: string, examinationType: ExaminationType, dermatologist: Dermatologist, id?: number) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.examinationTypeDTO = examinationType;
        this.dermatologistDTO = dermatologist;
        this.id=id;
    }
}