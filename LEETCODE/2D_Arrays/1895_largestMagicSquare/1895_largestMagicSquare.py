class Solution:
    def largestMagicSquare(self, grid: list[list[int]]) -> int:
        m, n = len(grid), len(grid[0])

        # Prefix sum matrices
        row_sum = [[0] * n for _ in range(m)]
        col_sum = [[0] * n for _ in range(m)]
        diag_sum = [[0] * n for _ in range(m)]
        anti_diag_sum = [[0] * n for _ in range(m)]

        # Build prefix sums
        for i in range(m):
            for j in range(n):
                row_sum[i][j] = grid[i][j] + (row_sum[i][j - 1] if j > 0 else 0)
                col_sum[i][j] = grid[i][j] + (col_sum[i - 1][j] if i > 0 else 0)
                diag_sum[i][j] = grid[i][j] + (diag_sum[i - 1][j - 1] if i > 0 and j > 0 else 0)
                anti_diag_sum[i][j] = (
                    grid[i][j] + (anti_diag_sum[i - 1][j + 1] if i > 0 and j < n - 1 else 0)
                )

        def is_magic(r: int, c: int, k: int) -> bool:
            # Target sum = first row sum
            target = row_sum[r][c + k - 1] - (row_sum[r][c - 1] if c > 0 else 0)

            # Check all rows
            for i in range(r, r + k):
                curr = row_sum[i][c + k - 1] - (row_sum[i][c - 1] if c > 0 else 0)
                if curr != target:
                    return False

            # Check all columns
            for j in range(c, c + k):
                curr = col_sum[r + k - 1][j] - (col_sum[r - 1][j] if r > 0 else 0)
                if curr != target:
                    return False

            # Check main diagonal (↘)
            diag = diag_sum[r + k - 1][c + k - 1] - (
                diag_sum[r - 1][c - 1] if r > 0 and c > 0 else 0
            )
            if diag != target:
                return False

            # Check anti-diagonal (↙)
            anti = anti_diag_sum[r + k - 1][c] - (
                anti_diag_sum[r - 1][c + k] if r > 0 and c + k < n else 0
            )
            if anti != target:
                return False

            return True

        ans = 1  # 1x1 is always magic

        for k in range(2, min(m, n) + 1):
            for r in range(m - k + 1):
                for c in range(n - k + 1):
                    if is_magic(r, c, k):
                        ans = k

        return ans
