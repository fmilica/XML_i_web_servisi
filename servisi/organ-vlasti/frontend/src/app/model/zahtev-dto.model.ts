export interface ZahtevDto {
    id: string,
    gradjaninEmail: string,
    nazivOrganaVlasti: string,
    sedisteOrganaVlasti: string,
    mesto: string,
    ulica: string,
    broj: string,
    datumZahteva: string,
    informacije: string,
    nazivPodnosioca?: string,
    imePodnosioca?: string,
    prezimePodnosioca?: string
}