import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_17609 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        String s = "";
        for (int i=0; i<t; i++){
            s=br.readLine();
            int result=check(s);
            sb.append(result).append("\n");
        }
        System.out.print(sb.toString());
    }

    public static int check(String s){ //summuus
        int left = 0;
        int right = s.length()-1;
        int sameCnt=0;
        int hopCnt=0;

        while (left<=right) {

            if (s.charAt(left) == s.charAt(right)){ //같은 경우
                sameCnt++;

                left++;
                right--;
            }
            else { //다른 경우

                boolean leftFound=false;
                boolean rightFound=false;

                //왼쪽 확인
                int tmpLeft = left;
                for (; tmpLeft<=s.length()/2; tmpLeft++){
                    if (s.charAt(tmpLeft) == s.charAt(right)) {
                        leftFound=true;
                        break;
                    }
                }

                //오른쪽 확인
                int tmpRight = right;
                for (; s.length()/2<=tmpRight; tmpRight--){
                    if (s.charAt(tmpRight) == s.charAt(left)) {
                        rightFound=true;
                        break;
                    }
                }

                //왼쪽과 오른쪽 중 더 원래 위치에서 가까운 놈을 선택한다.
                if (leftFound && rightFound){
                    if ((right-tmpRight)<(tmpLeft-left)){
                        sameCnt++;
                        hopCnt+=(right-tmpRight);
                    }
                    else {
                        sameCnt++;
                        hopCnt+=(tmpLeft-left);
                    }
                }
                else if (leftFound){
                    sameCnt++;
                    hopCnt+=(tmpLeft-left);
                }
                else if (rightFound){
                    sameCnt++;
                    hopCnt+=(tmpLeft-left);
                }
                else return 2;

            }
        }

        if (sameCnt==s.length()/2 && hopCnt==0){
            return 0;
        }
        else if (sameCnt==s.length()/2 && hopCnt==1) {
            return 1;
        }
        else {
            return 2;
        }
    }
}
