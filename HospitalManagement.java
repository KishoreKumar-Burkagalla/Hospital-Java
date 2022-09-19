
import java.util.Scanner;
import java.util.*;

public class HospitalManagement {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner sc = new Scanner(System.in);
        String s1 = new String();
        HashSet<String> hs = new HashSet<>();

        int L, M, N;
        System.out.print("Enter number of beds: ");
        L = sc.nextInt();
        System.out.print("Enter number of Doctors: ");
        M = sc.nextInt();
        System.out.print("Enter number of Patients: ");
        N = sc.nextInt();


        String beds_p[] = new String[L];
        int beds[] = new int[L];
        for (int i = 0; i < L; i++) {
            beds[i] = 0;
        }

        String patientName[] = new String[N];


        String whall[] = new String[L];

        String doctors[] = new String[M];

        System.out.println("Do you want to continue y{1}/n{0}");
        int choice = sc.nextInt();
        while (choice == 1) {

            System.out.println(" 1.Create Consultation\n 2.Doctor Decision\n 3.Discharge Patient\n 4.Send Patient to a consultation room\n 5.Info\n");
            int n = sc.nextInt();
            switch (n) {
                case 1:
                    s1 = consultation1(N);
                    break;
                case 2:
                    doctorDecision(hs, beds, beds_p, patientName);
                    break;
                case 3:
                    discharge(beds, beds_p, doctors, whall, patientName);
                    break;
                case 4:
                    consultationRoom(s1);
                    break;
                case 5:
                    info(hs, L, M, N, beds, beds_p, patientName, doctors, whall);
                default:
                    System.out.println("Invalid Entry");
            }


            int min = 0;
            if (L > N) {
                min = N;
            } else {
                min = L;
            }
            System.out.println("Bed number : Patient name");
            for (int i = 0; i < min; i++) {
                System.out.println("Bed-" + (i + 1) + " " + beds_p[i]);
            }
            System.out.println();
            int minD = 0;
            if (M > N) {
                minD = N;
            } else {
                minD = M;
            }


            System.out.println("Doctor : Patient name");
            for (int i = 0; i < minD; i++) {
                System.out.println("Doctor-" + (i + 1) + " " + beds_p[random.nextInt(beds_p.length)]);
            }

            System.out.println();


            System.out.println("WaitingHall : Patient name");
            for (int i = 0; i < N; i++) {
                System.out.println("WaitingHall Seat-" + (i + 1) + " " + patientName[i]);
            }

            System.out.println();
        }
    }


    private static String consultation1(int N) {
        Scanner sc = new Scanner(System.in);
        String patientName[] = new String[N];
        System.out.println("Enter patients names");
        for (int i = 0; i < N; i++) {
            patientName[i] = sc.next();
//            System.out.println("Patients name" + patientName[i]);
        }
//        Scanner scan = new Scanner(System.in);
//        System.out.println("Enter patient name to send for consultation (comma separated):\n");
//        String s = scan.next();
//        return s;
        return String.valueOf(patientName);
    }


    private static void doctorDecision(HashSet al, int[] beds, String beds_p[], String[] pname) {
        Scanner scan = new Scanner(System.in);
//        String s1[]=s.split(",");
        for (int i = 0; i < pname.length; i++) {
            System.out.println("Patient name: " + pname[i]);
//            int min = 1,max = 2;
//            Random r = new Random();
//            int n=r.nextInt((max - min) + 1) + min;
            System.out.println("whether you want to 1.Admit\n 2.Quarantine");
            int n = scan.nextInt();
            if (n == 1) {
                admit(beds, beds_p, pname[i]);
            } else {
                quarantine(al, pname[i]);
            }
        }
    }


    private static void admit(int[] beds, String[] beds_p, String pname) {
        for (int i = 0; i < beds.length; i++) {
            if (beds[i] == 0) {
                beds[i] = 1;
                beds_p[i] = pname;
                break;
            }
        }


    }

    private static void quarantine(HashSet hs, String pname) {
//        System.out.print(pname);
        hs.add(pname);
        System.out.println("The quarantined list: " + hs);
    }


    private static void discharge(int[] beds, String beds_p[], String doctors[], String whall[], String patientName[]) {
        System.out.println("Enter bed number: ");
        Scanner sc = new Scanner(System.in);
        int bno = sc.nextInt();
        if (bno < beds.length) {
            if (beds[bno] == 1) {
                String s = beds_p[bno];
                beds[bno] = 0;
                beds_p[bno] = "";
                whall[bno] = "";
                for (int i = 0; i < doctors.length; i++) {
                    if (doctors[i].equals(s) && doctors[i] != null) {
                        doctors[i] = "";
                        break;
                    }
                }
                for (int j = 0; j < patientName.length; j++) {
                    if (patientName[j].equals(s)) {
                        patientName[j] = "";
                        break;
                    }
                }
            }
        } else {
            System.out.println("Bed number doesnot exist");
        }

    }


    private static void consultationRoom(String s1) {
        System.out.println("The patients in consultaion room are: ");
        System.out.println(s1);
    }


    private static void info(HashSet hs, int L, int M, int N, int[] beds, String[] beds_p, String patientName[], String[] doctors, String[] whall) {
        HashSet<Integer> h = new HashSet<>();
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);

        int min1 = (L < N) ? L : N;
        int min2 = (N < M) ? N : M;
        int p = 0;
        for (int i = 0; i < min1 || i < min2; i++) {
            if (beds[i] == 0) {
                beds_p[i] = patientName[i];
                beds[i] = 1;
                int k = 0;
                for (int j = 0; j < M; j++) {
                    k = rand.nextInt(M);
                    if (!h.contains(k)) {
                        h.add(k);
                        break;
                    } else {
                        continue;
                    }
                }
                if (doctors[k] == null) {
                    doctors[k] = patientName[i];
                }
            }
            p = i;
            whall[i] = patientName[i];
        }
        if (N > L) {
            for (p = p + 1; p < N; p++) {
                quarantine(hs, patientName[p]);
                System.out.print("Patients in queue : ");
                System.out.print(patientName[p] + "  ");
            }
        }
    }
}
				